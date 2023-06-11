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
package cn.ecnu.sys.modular.user.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户结果集
 *
 * @author xuyuxiang
 * @date 2022/7/8 13:22
 **/
@Getter
@Setter
public class SysUserResult {

    /** id */
    @ApiModelProperty(value = "id", position = 1)
    private String id;

    /** 头像 */
    @ApiModelProperty(value = "头像", position = 3)
    private String avatar;

    /** 签名 */
    @ApiModelProperty(value = "签名", position = 4)
    private String signature;

    /** 账号 */
    @ApiModelProperty(value = "账号", position = 5)
    private String account;

    /** 姓名 */
    @ApiModelProperty(value = "姓名", position = 7)
    private String name;

    /** 昵称 */
    @ApiModelProperty(value = "昵称", position = 8)
    private String nickname;

    /** 性别 */
    @ApiModelProperty(value = "性别", position = 9)
    private String gender;

    /** 年龄 */
    @ApiModelProperty(value = "年龄", position = 10)
    private String age;

    /** 出生日期 */
    @ApiModelProperty(value = "出生日期", position = 11)
    private String birthday;

    /** 民族 */
    @ApiModelProperty(value = "民族", position = 12)
    private String nation;

    /** 籍贯 */
    @ApiModelProperty(value = "籍贯", position = 13)
    private String nativePlace;

    /** 家庭住址 */
    @ApiModelProperty(value = "家庭住址", position = 14)
    private String homeAddress;

    /** 通信地址 */
    @ApiModelProperty(value = "通信地址", position = 15)
    private String mailingAddress;

    /** 证件类型 */
    @ApiModelProperty(value = "证件类型", position = 16)
    private String idCardType;

    /** 证件号码 */
    @ApiModelProperty(value = "证件号码", position = 17)
    private String idCardNumber;

    /** 文化程度 */
    @ApiModelProperty(value = "文化程度", position = 18)
    private String cultureLevel;

    /** 政治面貌 */
    @ApiModelProperty(value = "政治面貌", position = 19)
    private String politicalOutlook;

    /** 毕业院校 */
    @ApiModelProperty(value = "毕业院校", position = 20)
    private String college;

    /** 学历 */
    @ApiModelProperty(value = "学历", position = 21)
    private String education;

    /** 学制 */
    @ApiModelProperty(value = "学制", position = 22)
    private String eduLength;

    /** 学位 */
    @ApiModelProperty(value = "学位", position = 23)
    private String degree;

    /** 手机 */
    @ApiModelProperty(value = "手机", position = 24)
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", position = 25)
    private String email;

    /** 家庭电话 */
    @ApiModelProperty(value = "家庭电话", position = 26)
    private String homeTel;

    /** 办公电话 */
    @ApiModelProperty(value = "办公电话", position = 27)
    private String officeTel;

    /** 紧急联系人 */
    @ApiModelProperty(value = "紧急联系人", position = 28)
    private String emergencyContact;

    /** 紧急联系人电话 */
    @ApiModelProperty(value = "紧急联系人电话", position = 29)
    private String emergencyPhone;

    /** 紧急联系人地址 */
    @ApiModelProperty(value = "紧急联系人地址", position = 30)
    private String emergencyAddress;

    /** 员工编号 */
    @ApiModelProperty(value = "员工编号", position = 31)
    private String empNo;

    /** 入职日期 */
    @ApiModelProperty(value = "入职日期", position = 32)
    private String entryDate;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 33)
    private String orgId;

    /** 职位id */
    @ApiModelProperty(value = "职位id", position = 34)
    private String positionId;

    /** 职级 */
    @ApiModelProperty(value = "职级", position = 35)
    private String positionLevel;

    /** 主管id */
    @ApiModelProperty(value = "主管id", position = 36)
    private String directorId;

    /** 上次登录ip */
    @ApiModelProperty(value = "上次登录ip", position = 37)
    private String lastLoginIp;

    /** 上次登录地点 */
    @ApiModelProperty(value = "上次登录地点", position = 38)
    private String lastLoginAddress;

    /** 上次登录时间 */
    @ApiModelProperty(value = "上次登录时间", position = 39)
    private Date lastLoginTime;

    /** 上次登录设备 */
    @ApiModelProperty(value = "上次登录设备", position = 40)
    private String lastLoginDevice;

    /** 最新登录ip */
    @ApiModelProperty(value = "最新登录ip", position = 41)
    private String latestLoginIp;

    /** 最新登录地点 */
    @ApiModelProperty(value = "最新登录地点", position = 42)
    private String latestLoginAddress;

    /** 最新登录时间 */
    @ApiModelProperty(value = "最新登录时间", position = 43)
    private Date latestLoginTime;

    /** 最新登录设备 */
    @ApiModelProperty(value = "最新登录设备", position = 44)
    private String latestLoginDevice;

    /** 用户状态 */
    @ApiModelProperty(value = "用户状态", position = 45)
    private String userStatus;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 46)
    private Integer sortCode;

    /** 扩展信息 */
    @ApiModelProperty(value = "扩展信息", position = 47)
    private String extJson;

    /* ====额外的字段==== */

    /** 组织名称 */
    @ApiModelProperty(value = "组织名称", position = 48)
    private String orgName;

    /** 职位名称 */
    @ApiModelProperty(value = "职位名称", position = 49)
    private String positionName;

    /** 主管名称 */
    @ApiModelProperty(value = "主管名称", position = 50)
    private String directorName;
}
