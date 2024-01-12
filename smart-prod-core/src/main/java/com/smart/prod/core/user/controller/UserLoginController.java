package com.smart.prod.core.user.controller;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.smart.prod.common.exception.Checker;
import com.smart.prod.common.exception.Errors;
import com.smart.prod.common.response.CommonCode;
import com.smart.prod.common.utils.R;
import com.smart.prod.mbg.auth.util.StpUserUtil;
import com.smart.prod.mbg.domain.dto.UserLoginParam;
import com.smart.prod.mbg.domain.dto.UserRegisterParam;
import com.smart.prod.mbg.domain.entity.User;
import com.smart.prod.mbg.domain.vo.UserLoginVO;
import com.smart.prod.mbg.domain.vo.UserInfo;
import com.smart.prod.mbg.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @Author: Guoji Shen
 * @Date: 2023/6/27 16:33
 */
@RestController
@RequestMapping("/sso")
@Tag(name = "登录")
public class UserLoginController {

    @Autowired
    private SaTokenDao saTokenDao;
    @Autowired
    private IUserService userService;

    @Operation(summary = "注册")
    @PostMapping(value = "/register")
    public R register(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        userService.register(userRegisterParam);
        return R.ok();
    }

    @Operation(summary = "登录")
    @PostMapping(value = "/login")
    public R login(@Valid @RequestBody UserLoginParam userLoginParam) {
        // 根据手机号查询用户
        User user = new LambdaQueryChainWrapper<>(userService.getBaseMapper()).eq(User::getUsername, userLoginParam.getUsername()).one();

        // 校验用户
        Checker.ifNullThrow(user, () -> Errors.BIZ.exception("该用户不存在!"));
        Checker.ifThrow(user.getStatus().equals(0), () -> Errors.BIZ.exception("该用户已被禁用!"));

        // 校验密码
        Checker.ifNotThrow(BCrypt.checkpw(userLoginParam.getPassword(), user.getPassword()),
            () -> Errors.BIZ.exception(CommonCode.PASSWORD_VALID_ERROR));

        // 登录
        StpUserUtil.login(user.getId());
        // 将用户信息放入缓存
        UserInfo userInfo = UserInfo.builder()
            .id(user.getId())
            .username(user.getUsername())
            .icon(user.getIcon())
            .permission(StpUserUtil.getPermissionList())
            .roleList(StpUserUtil.getRoleList()).build();
        saTokenDao.setObject(StpUserUtil.getLoginId().toString(), userInfo, SaTokenDao.NEVER_EXPIRE);

        // 返回token与用户信息
        return R.ok().data(UserLoginVO.builder()
            .token(StpUserUtil.getTokenValue())
            .userInfo(userInfo)
            .build());
    }

    @Operation(summary = "退出登录")
    @PostMapping(value = "/logout")
    public R logout() {
        try {
            saTokenDao.deleteObject(StpUserUtil.getLoginId().toString());
        } finally {
            // 登出
            StpUserUtil.logout(StpUserUtil.getLoginId());
        }
        return R.ok();
    }
}