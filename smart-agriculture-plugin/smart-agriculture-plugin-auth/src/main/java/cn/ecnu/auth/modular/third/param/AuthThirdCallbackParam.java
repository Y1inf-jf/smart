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
package cn.ecnu.auth.modular.third.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 第三方登录回调参数
 *
 * @author xuyuxiang
 * @date 2022/7/8 20:38
 */
@Getter
@Setter
public class AuthThirdCallbackParam {

    /** 第三方平台标识 */
    @ApiModelProperty(value = "第三方平台标识", required = true, position = 1)
    @NotBlank(message = "platform不能为空")
    private String platform;

    /** 第三方回调code */
    @ApiModelProperty(value = "第三方回调code", required = true, position = 2)
    @NotBlank(message = "code不能为空")
    private String code;

    /** 第三方回调state */
    @ApiModelProperty(value = "第三方回调state", required = true, position = 3)
    @NotBlank(message = "state不能为空")
    private String state;
}
