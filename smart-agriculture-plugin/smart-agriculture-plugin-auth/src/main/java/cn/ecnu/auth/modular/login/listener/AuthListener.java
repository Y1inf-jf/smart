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
package cn.ecnu.auth.modular.login.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Component;
import cn.ecnu.auth.api.SaBaseLoginUserApi;
import cn.ecnu.auth.core.enums.SaClientTypeEnum;
import cn.ecnu.auth.core.pojo.SaBaseLoginUser;
import cn.ecnu.dev.api.DevLogApi;

import javax.annotation.Resource;

/**
 * 自定义登录监听器
 *
 * @author xuyuxiang
 * @date 2021/12/28 11:35
 **/
@Component
public class AuthListener implements SaTokenListener {

    @Resource(name = "loginUserApi")
    private SaBaseLoginUserApi loginUserApi;

    @Resource(name = "clientLoginUserApi")
    private SaBaseLoginUserApi clientLoginUserApi;

    @Resource
    private DevLogApi devLogApi;

    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel)  {
        // 更新用户的登录时间和登录ip等信息
        if(SaClientTypeEnum.B.getValue().equals(loginType)) {
            loginUserApi.updateUserLoginInfo(Convert.toStr(loginId), loginModel.getDevice());
            // 记录B端登录日志
            SaBaseLoginUser saBaseLoginUser = loginUserApi.getUserById(Convert.toStr(loginId));
            if(ObjectUtil.isNotEmpty(saBaseLoginUser)) {
                devLogApi.executeLoginLog(saBaseLoginUser.getName());
            } else {
                devLogApi.executeLoginLog(null);
            }
        } else {
            clientLoginUserApi.updateUserLoginInfo(Convert.toStr(loginId), loginModel.getDevice());
        }
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        if(SaClientTypeEnum.B.getValue().equals(loginType)) {
            // 记录B端登出日志
            SaBaseLoginUser saBaseLoginUser = loginUserApi.getUserById(Convert.toStr(loginId));
            if(ObjectUtil.isNotEmpty(saBaseLoginUser)) {
                devLogApi.executeLogoutLog(saBaseLoginUser.getName());
            } else {
                devLogApi.executeLogoutLog(null);
            }
        }
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        // ...
    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        // ...
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {
        // ...
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId) {
        // ...
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        // ...
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        // ...
    }
}
