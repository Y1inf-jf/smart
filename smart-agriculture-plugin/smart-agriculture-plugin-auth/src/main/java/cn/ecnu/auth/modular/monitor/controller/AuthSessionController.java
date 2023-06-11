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
package cn.ecnu.auth.modular.monitor.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cn.ecnu.auth.modular.monitor.param.AuthExitSessionParam;
import cn.ecnu.auth.modular.monitor.param.AuthExitTokenParam;
import cn.ecnu.auth.modular.monitor.param.AuthSessionPageParam;
import cn.ecnu.auth.modular.monitor.result.AuthSessionAnalysisResult;
import cn.ecnu.auth.modular.monitor.result.AuthSessionPageResult;
import cn.ecnu.auth.modular.monitor.service.AuthSessionService;
import cn.ecnu.common.annotation.CommonLog;
import cn.ecnu.common.pojo.CommonResult;
import cn.ecnu.common.pojo.CommonValidList;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 会话治理控制器
 *
 * @author xuyuxiang
 * @date 2022/6/24 15:20
 **/
@Api(tags = "会话治理控制器")
@ApiSupport(author = "SNOWY_TEAM", order = 3)
@RestController
@Validated
public class AuthSessionController {

    @Resource
    private AuthSessionService authSessionService;

    /**
     * 会话统计
     *
     * @author xuyuxiang
     * @date 2022/6/24 22:28
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("会话统计")
    @GetMapping("/auth/session/analysis")
    public CommonResult<AuthSessionAnalysisResult> analysis() {
        return CommonResult.data(authSessionService.analysis());
    }

    /**
     * 查询B端会话
     *
     * @author xuyuxiang
     * @date 2022/6/24 22:28
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("查询B端会话")
    @GetMapping("/auth/session/b/page")
    public CommonResult<Page<AuthSessionPageResult>> pageForB(AuthSessionPageParam authSessionPageParam) {
        return CommonResult.data(authSessionService.pageForB(authSessionPageParam));
    }

    /**
     * 查询C端会话
     *
     * @author xuyuxiang
     * @date 2022/6/24 22:28
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("查询C端会话")
    @GetMapping("/auth/session/c/page")
    public CommonResult<Page<AuthSessionPageResult>> pageForC(AuthSessionPageParam authSessionPageParam) {
        return CommonResult.data(authSessionService.pageForC(authSessionPageParam));
    }

    /**
     * 强退B端会话
     *
     * @author xuyuxiang
     * @date 2021/10/12 10:25
     **/
    @ApiOperationSupport(order = 4)
    @ApiOperation("强退B端会话")
    @CommonLog("强退B端会话")
    @PostMapping("/auth/session/b/exit")
    public CommonResult<String> exitSessionForB(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                            CommonValidList<AuthExitSessionParam> authExitSessionParamList) {
        authSessionService.exitSessionForB(authExitSessionParamList);
        return CommonResult.ok();
    }

    /**
     * 强退C端会话
     *
     * @author xuyuxiang
     * @date 2021/10/12 10:25
     **/
    @ApiOperationSupport(order = 5)
    @ApiOperation("强退C端会话")
    @CommonLog("强退C端会话")
    @PostMapping("/auth/session/c/exit")
    public CommonResult<String> exitSessionForC(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                            CommonValidList<AuthExitSessionParam> authExitSessionParamList) {
        authSessionService.exitSessionForC(authExitSessionParamList);
        return CommonResult.ok();
    }

    /**
     * 强退B端token
     *
     * @author xuyuxiang
     * @date 2021/10/12 10:25
     **/
    @ApiOperationSupport(order = 6)
    @ApiOperation("强退B端token")
    @CommonLog("强退B端token")
    @PostMapping("/auth/token/b/exit")
    public CommonResult<String> exitTokenForB(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                            CommonValidList<AuthExitTokenParam> authExitTokenParamList) {
        authSessionService.exitTokenForB(authExitTokenParamList);
        return CommonResult.ok();
    }

    /**
     * 强退C端token
     *
     * @author xuyuxiang
     * @date 2021/10/12 10:25
     **/
    @ApiOperationSupport(order = 7)
    @ApiOperation("强退C端token")
    @CommonLog("强退C端token")
    @PostMapping("/auth/token/c/exit")
    public CommonResult<String> exitTokenForC(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                                            CommonValidList<AuthExitTokenParam> authExitTokenParamList) {
        authSessionService.exitTokenForC(authExitTokenParamList);
        return CommonResult.ok();
    }
}
