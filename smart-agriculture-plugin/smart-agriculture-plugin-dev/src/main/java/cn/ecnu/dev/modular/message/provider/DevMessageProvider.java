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
package cn.ecnu.dev.modular.message.provider;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import cn.ecnu.dev.api.DevMessageApi;
import cn.ecnu.dev.modular.message.param.DevMessageIdParam;
import cn.ecnu.dev.modular.message.param.DevMessageListParam;
import cn.ecnu.dev.modular.message.param.DevMessageSendParam;
import cn.ecnu.dev.modular.message.service.DevMessageService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 站内信API接口提供者
 *
 * @author xuyuxiang
 * @date 2022/6/22 15:32
 **/
@Service
public class DevMessageProvider implements DevMessageApi {

    @Resource
    private DevMessageService devMessageService;

    @Override
    public void sendMessage(List<String> receiverIdList, String subject) {
        this.sendMessage(receiverIdList, subject, null);
    }

    @Override
    public void sendMessage(List<String> receiverIdList, String subject, String content) {
        this.sendMessageWithContent(receiverIdList, subject, null);
    }

    @Override
    public void sendMessageWithContent(List<String> receiverIdList, String subject, String content) {
        this.sendMessageWithContent(receiverIdList, null, subject, content);
    }

    @Override
    public void sendMessageWithContent(List<String> receiverIdList, String category, String subject, String content) {
        DevMessageSendParam devMessageSendParam = new DevMessageSendParam();
        devMessageSendParam.setReceiverIdList(receiverIdList);
        devMessageSendParam.setCategory(category);
        devMessageSendParam.setSubject(subject);
        devMessageSendParam.setContent(ObjectUtil.isEmpty(content)?subject:content);
        devMessageService.send(devMessageSendParam);
    }

    @Override
    public List<JSONObject> list(List<String> receiverIdList, Integer limit) {
        DevMessageListParam devMessageListParam = new DevMessageListParam();
        devMessageListParam.setReceiverIdList(receiverIdList);
        devMessageListParam.setLimit(limit);
        return devMessageService.list(devMessageListParam).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    @Override
    public Page<JSONObject> page(List<String> receiverIdList, String category) {
        return devMessageService.page(receiverIdList, category);
    }

    @Override
    public JSONObject detail(String id) {
        DevMessageIdParam devMessageIdParam = new DevMessageIdParam();
        devMessageIdParam.setId(id);
        return JSONUtil.parseObj(devMessageService.detail(devMessageIdParam));
    }
}
