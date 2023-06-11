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
package cn.ecnu.sys.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.ecnu.sys.modular.resource.entity.SysButton;
import cn.ecnu.sys.modular.resource.param.button.SysButtonAddParam;
import cn.ecnu.sys.modular.resource.param.button.SysButtonEditParam;
import cn.ecnu.sys.modular.resource.param.button.SysButtonIdParam;
import cn.ecnu.sys.modular.resource.param.button.SysButtonPageParam;

import java.util.List;

/**
 * 按钮Service接口
 *
 * @author xuyuxiang
 * @date 2022/6/27 14:01
 **/
public interface SysButtonService extends IService<SysButton> {

    /**
     * 获取按钮分页
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:08
     */
    Page<SysButton> page(SysButtonPageParam sysButtonPageParam);

    /**
     * 添加按钮
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:48
     */
    void add(SysButtonAddParam sysButtonAddParam);

    /**
     * 编辑按钮
     *
     * @author xuyuxiang
     * @date 2022/4/24 21:13
     */
    void edit(SysButtonEditParam sysButtonEditParam);

    /**
     * 删除按钮
     *
     * @author xuyuxiang
     * @date 2022/4/24 21:18
     */
    void delete(List<SysButtonIdParam> sysButtonIdParamList);

    /**
     * 获取按钮详情
     *
     * @author xuyuxiang
     * @date 2022/4/24 21:18
     */
    SysButton detail(SysButtonIdParam sysButtonIdParam);

    /**
     * 获取按钮详情
     *
     * @author xuyuxiang
     * @date 2022/4/24 21:18
     */
    SysButton queryEntity(String id);
}
