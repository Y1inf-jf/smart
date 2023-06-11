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
package cn.ecnu.dev.modular.sms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.ecnu.common.enums.CommonSortOrderEnum;
import cn.ecnu.common.exception.CommonException;
import cn.ecnu.common.page.CommonPageRequest;
import cn.ecnu.dev.modular.sms.entity.DevSms;
import cn.ecnu.dev.modular.sms.enums.DevSmsEngineTypeEnum;
import cn.ecnu.dev.modular.sms.mapper.DevSmsMapper;
import cn.ecnu.dev.modular.sms.param.DevSmsIdParam;
import cn.ecnu.dev.modular.sms.param.DevSmsPageParam;
import cn.ecnu.dev.modular.sms.param.DevSmsSendAliyunParam;
import cn.ecnu.dev.modular.sms.param.DevSmsSendTencentParam;
import cn.ecnu.dev.modular.sms.service.DevSmsService;
import cn.ecnu.dev.modular.sms.util.DevSmsAliyunUtil;
import cn.ecnu.dev.modular.sms.util.DevSmsTencentUtil;

import java.util.List;

/**
 * 短信Service接口实现类
 *
 * @author xuyuxiang
 * @date 2022/2/23 18:43
 **/
@Service
public class DevSmsServiceImpl extends ServiceImpl<DevSmsMapper, DevSms> implements DevSmsService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendAliyun(DevSmsSendAliyunParam devSmsSendAliyunParam) {
        validPhone(devSmsSendAliyunParam.getPhoneNumbers());
        String receiptInfo = DevSmsAliyunUtil.sendSms(devSmsSendAliyunParam.getPhoneNumbers(), devSmsSendAliyunParam.getSignName(),
                devSmsSendAliyunParam.getTemplateCode(), devSmsSendAliyunParam.getTemplateParam());
        DevSms devSms = new DevSms();
        BeanUtil.copyProperties(devSmsSendAliyunParam, devSms);
        devSms.setEngine(DevSmsEngineTypeEnum.ALIYUN.getValue());
        devSms.setReceiptInfo(receiptInfo);
        this.save(devSms);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendTencent(DevSmsSendTencentParam smsSendTencentParam) {
        validPhone(smsSendTencentParam.getPhoneNumbers());
        String receiptInfo =DevSmsTencentUtil.sendSms(smsSendTencentParam.getSdkAppId(), smsSendTencentParam.getPhoneNumbers(),
                smsSendTencentParam.getSignName(), smsSendTencentParam.getTemplateCode(), smsSendTencentParam.getTemplateParam());
        DevSms devSms = new DevSms();
        BeanUtil.copyProperties(smsSendTencentParam, devSms);
        devSms.setEngine(DevSmsEngineTypeEnum.TENCENT.getValue());
        devSms.setReceiptInfo(receiptInfo);
        this.save(devSms);
    }

    @Override
    public Page<DevSms> page(DevSmsPageParam devSmsPageParam) {
        QueryWrapper<DevSms> queryWrapper = new QueryWrapper<>();
        if(ObjectUtil.isNotEmpty(devSmsPageParam.getEngine())) {
            queryWrapper.lambda().eq(DevSms::getEngine, devSmsPageParam.getEngine());
        }
        if(ObjectUtil.isNotEmpty(devSmsPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevSms::getPhoneNumbers, devSmsPageParam.getSearchKey());
        }
        if(ObjectUtil.isAllNotEmpty(devSmsPageParam.getSortField(), devSmsPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devSmsPageParam.getSortOrder());
            queryWrapper.orderBy(true, devSmsPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devSmsPageParam.getSortField()));
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void delete(List<DevSmsIdParam> devSmsIdParamList) {
        this.removeBatchByIds(CollStreamUtil.toList(devSmsIdParamList, DevSmsIdParam::getId));
    }

    @Override
    public DevSms detail(DevSmsIdParam devSmsIdParam) {
        return this.queryEntity(devSmsIdParam.getId());
    }

    @Override
    public DevSms queryEntity(String id) {
        DevSms devSms = this.getById(id);
        if(ObjectUtil.isEmpty(devSms)) {
            throw new CommonException("短信不存在，id值为：{}", id);
        }
        return devSms;
    }

    /**
     * 校验手机格式
     *
     * @author xuyuxiang
     * @date 2022/8/15 13:32
     **/
    private void validPhone(String phones) {
        StrUtil.split(phones, StrUtil.COMMA).forEach(phone -> {
            if(!PhoneUtil.isMobile(phone)) {
                throw new CommonException("手机号码：{}格式错误", phone);
            }
        });
    }
}
