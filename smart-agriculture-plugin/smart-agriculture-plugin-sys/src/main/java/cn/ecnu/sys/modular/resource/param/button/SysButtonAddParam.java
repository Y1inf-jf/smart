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
package cn.ecnu.sys.modular.resource.param.button;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 按钮添加参数
 *
 * @author xuyuxiang
 * @date 2022/7/27 18:40
 **/
@Getter
@Setter
public class SysButtonAddParam {

    /** 父id */
    @ApiModelProperty(value = "父id", required = true, position = 1)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /** 标题 */
    @ApiModelProperty(value = "标题", required = true, position = 2)
    @NotBlank(message = "title不能为空")
    private String title;

    /** 编码 */
    @ApiModelProperty(value = "编码", required = true, position = 3)
    @NotBlank(message = "code不能为空")
    private String code;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", required = true, position = 4)
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /** 扩展JSON */
    @ApiModelProperty(value = "扩展JSON", position = 5)
    private String extJson;
}
