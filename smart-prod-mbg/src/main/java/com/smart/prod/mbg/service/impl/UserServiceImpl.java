package com.smart.prod.mbg.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.prod.common.constanst.Constanst;
import com.smart.prod.common.exception.Checker;
import com.smart.prod.common.exception.Errors;
import com.smart.prod.common.utils.Str;
import com.smart.prod.mbg.domain.criteria.UserCriteria;
import com.smart.prod.mbg.domain.dto.UserCreateParam;
import com.smart.prod.mbg.domain.dto.UserRegisterParam;
import com.smart.prod.mbg.domain.entity.Role;
import com.smart.prod.mbg.domain.entity.User;
import com.smart.prod.mbg.domain.vo.UserVO;
import com.smart.prod.mbg.mapper.UserMapper;
import com.smart.prod.mbg.service.IRoleMenuService;
import com.smart.prod.mbg.service.IUserRoleService;
import com.smart.prod.mbg.service.IUserService;
import com.smart.prod.mbg.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IUserRoleService userRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User register(UserRegisterParam userRegisterParam) {
        // 校验用户名是否重复
        checkUsername(userRegisterParam.getUsername());
        // 校验验证码
        User user = User.builder()
            .username(userRegisterParam.getUsername())
            .password(BCrypt.hashpw(userRegisterParam.getPassword()))
            .phone(userRegisterParam.getPhone())
            .gender(userRegisterParam.getGender())
            .status(1)
            .build();
        baseMapper.insert(user);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStatus(String id, Integer status) {
        User user = baseMapper.selectById(id);
        user.setStatus(status);
        baseMapper.updateById(user);
    }

    @Override
    public Map<String, Object> queryAll(UserCriteria criteria, Page page) {
        IPage<UserVO> iPage = baseMapper.queryAll(criteria, page);
        return PageUtil.toPage(iPage);
    }

    @Override
    public User findById(String id) {
        User user = baseMapper.findById(id);
        user.setRoleList(roleMenuService.getRoleListByUserId(user.getId()).stream().map(Role::getId).toList());
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(UserCreateParam user) {
        // 校验用户名是否重复
        checkUsername(user.getUsername());
        // 若密码为空则给默认密码
        String password = Str.isNotBlank(user.getPassword()) ? BCrypt.hashpw(user.getPassword()) : BCrypt.hashpw(
            Constanst.DEFAULT_PASSWORD);
        user.setPassword(password);
        baseMapper.insert(user);
        // 角色绑定
        userRoleService.bindRole(user.getId(), user.getRoleList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserCreateParam user) {
        baseMapper.updateById(user);
        // 角色绑定
        userRoleService.bindRole(user.getId(), user.getRoleList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(String id, String password) {
        // 若未传入密码，则设置默认密码
        password = Optional.ofNullable(password).orElse(Constanst.DEFAULT_PASSWORD);
        User user = baseMapper.selectById(id);
        Checker.ifNullThrow(user, () -> Errors.BIZ.exception("该用户不存在!"));
        user.setPassword(BCrypt.hashpw(password));
        baseMapper.updateById(user);
    }

    /**
     * 校验是否重名
     * @param username
     * @return
     */
    private String createRandomUserName(String username) {
        return this.checkUsername(username) ? createRandomUserName(RandomUtil.randomString(30)) : username;
    }
    private boolean checkUsername(String username) {
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return count > 0;
    }
}
