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
package cn.ecnu.dev.modular.message.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 站内信发送参数
 *
 * @author xuyuxiang
 * @date 2022/6/21 15:34
 **/
@Getter
@Setter
public class DevMessageSendParam {

    /** 主题 */
    @ApiModelProperty(value = "主题", required = true, position = 1)
    @NotBlank(message = "subject不能为空")
    private String subject;

    /** 接收人id集合 */
    @ApiModelProperty(value = "接收人id集合", required = true, position = 2)
    @NotEmpty(message = "receiverIdList不能为空")
    private List<String> receiverIdList;

    /** 正文 */
    @ApiModelProperty(value = "正文", position = 3)
    private String content;

    /** 分类 */
    @ApiModelProperty(value = "分类", position = 4, hidden = true)
    @NotBlank(message = "category不能为空")
    private String category;
}
