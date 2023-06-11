/*
 * Copyright [2022] [https://www.xiaonuo.vip]
 *
 * Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改Snowy源码头部的版权声明。
 * 3.本项目代码可免费商业使用，商业使用请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 https://www.xiaonuo.vip
 * 5.不可二次分发开源参与同类竞品，如有想法可联系团队xiaonuobase@qq.com商议合作。
 * 6.若您的项目无法满足以上几点，需要更多功能代码，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package cn.ecnu.sys.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.ecnu.common.enums.CommonSortOrderEnum;
import cn.ecnu.common.exception.CommonException;
import cn.ecnu.common.page.CommonPageRequest;
import cn.ecnu.sys.modular.relation.entity.SysRelation;
import cn.ecnu.sys.modular.relation.enums.SysRelationCategoryEnum;
import cn.ecnu.sys.modular.relation.service.SysRelationService;
import cn.ecnu.sys.modular.resource.entity.SysButton;
import cn.ecnu.sys.modular.resource.entity.SysMenu;
import cn.ecnu.sys.modular.resource.enums.SysResourceCategoryEnum;
import cn.ecnu.sys.modular.resource.mapper.SysButtonMapper;
import cn.ecnu.sys.modular.resource.param.button.SysButtonAddParam;
import cn.ecnu.sys.modular.resource.param.button.SysButtonEditParam;
import cn.ecnu.sys.modular.resource.param.button.SysButtonIdParam;
import cn.ecnu.sys.modular.resource.param.button.SysButtonPageParam;
import cn.ecnu.sys.modular.resource.service.SysButtonService;
import cn.ecnu.sys.modular.resource.service.SysMenuService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 按钮Service接口实现类
 *
 * @author xuyuxiang
 * @date 2022/6/27 14:25
 **/
@Service
public class SysButtonServiceImpl extends ServiceImpl<SysButtonMapper, SysButton> implements SysButtonService {

    @Resource
    private SysRelationService sysRelationService;

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public Page<SysButton> page(SysButtonPageParam sysButtonPageParam) {
        QueryWrapper<SysButton> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysButton::getCategory, SysResourceCategoryEnum.BUTTON.getValue());
        if(ObjectUtil.isNotEmpty(sysButtonPageParam.getParentId())) {
            queryWrapper.lambda().eq(SysButton::getParentId, sysButtonPageParam.getParentId());
        }
        if(ObjectUtil.isNotEmpty(sysButtonPageParam.getSearchKey())) {
            queryWrapper.lambda().like(SysButton::getTitle, sysButtonPageParam.getSearchKey());
        }
        if(ObjectUtil.isAllNotEmpty(sysButtonPageParam.getSortField(), sysButtonPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sysButtonPageParam.getSortOrder());
            queryWrapper.orderBy(true, sysButtonPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sysButtonPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SysButton::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void add(SysButtonAddParam sysButtonAddParam) {
        SysButton sysButton = BeanUtil.toBean(sysButtonAddParam, SysButton.class);
        boolean repeatCode = this.count(new LambdaQueryWrapper<SysButton>()
                .eq(SysButton::getCategory, SysResourceCategoryEnum.BUTTON.getValue())
                .eq(SysButton::getCode, sysButton.getCode())) > 0;
        if(repeatCode) {
            throw new CommonException("存在重复的按钮，编码为：{}", sysButton.getCode());
        }
        sysButton.setCategory(SysResourceCategoryEnum.BUTTON.getValue());
        this.save(sysButton);
    }

    @Override
    public void edit(SysButtonEditParam sysButtonEditParam) {
        SysButton sysButton = this.queryEntity(sysButtonEditParam.getId());
        BeanUtil.copyProperties(sysButtonEditParam, sysButton);
        boolean repeatCode = this.count(new LambdaQueryWrapper<SysButton>()
                .eq(SysButton::getCategory, SysResourceCategoryEnum.BUTTON.getValue())
                .eq(SysButton::getCode, sysButton.getCode())
                .ne(SysButton::getId, sysButtonEditParam.getId())) > 0;
        if(repeatCode) {
            throw new CommonException("存在重复的按钮，编码为：{}", sysButton.getCode());
        }
        this.updateById(sysButton);
    }

    @Override
    public void delete(List<SysButtonIdParam> sysButtonIdParamList) {
        List<String> buttonIdList = CollStreamUtil.toList(sysButtonIdParamList, SysButtonIdParam::getId);
        if(ObjectUtil.isNotEmpty(buttonIdList)) {
            // 获取按钮的父菜单id集合
            List<String> parentMenuIdList = sysMenuService.list(new LambdaUpdateWrapper<SysMenu>().in(SysMenu::getId, buttonIdList)
                    .eq(SysMenu::getCategory, SysResourceCategoryEnum.BUTTON.getValue())).stream().map(SysMenu::getParentId)
                    .collect(Collectors.toList());
            if(ObjectUtil.isNotEmpty(parentMenuIdList)) {
                sysRelationService.list(new LambdaUpdateWrapper<SysRelation>().in(SysRelation::getTargetId, parentMenuIdList)
                        .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_ROLE_HAS_RESOURCE.getValue())
                        .isNotNull(SysRelation::getExtJson)).forEach(sysRelation -> {
                    JSONObject extJsonObject = JSONUtil.parseObj(sysRelation.getExtJson());
                    List<String> buttonInfoList = extJsonObject.getBeanList("buttonInfo", String.class);
                    if (ObjectUtil.isNotEmpty(buttonInfoList)) {
                        Set<String> intersectionDistinct = CollectionUtil.intersectionDistinct(buttonIdList, buttonInfoList);
                        if(ObjectUtil.isNotEmpty(intersectionDistinct)) {
                            Collection<String> disjunction = CollectionUtil.disjunction(buttonInfoList, intersectionDistinct);
                            extJsonObject.set("buttonInfo", disjunction);
                        }
                    }
                    // 清除对应的角色与资源信息中的【授权的按钮信息】
                    sysRelationService.update(new LambdaUpdateWrapper<SysRelation>().eq(SysRelation::getId, sysRelation.getId())
                            .set(SysRelation::getExtJson, JSONUtil.toJsonStr(extJsonObject)));
                });
                // 执行删除
                this.removeBatchByIds(buttonIdList);
            }
        }
    }

    @Override
    public SysButton detail(SysButtonIdParam sysButtonIdParam) {
        return this.queryEntity(sysButtonIdParam.getId());
    }

    @Override
    public SysButton queryEntity(String id) {
        SysButton sysButton = this.getById(id);
        if(ObjectUtil.isEmpty(sysButton)) {
            throw new CommonException("按钮不存在，id值为：{}", id);
        }
        return sysButton;
    }
}
