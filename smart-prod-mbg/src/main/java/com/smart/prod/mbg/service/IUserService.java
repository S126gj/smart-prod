package com.smart.prod.mbg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smart.prod.mbg.domain.criteria.UserCriteria;
import com.smart.prod.mbg.domain.dto.UserCreateParam;
import com.smart.prod.mbg.domain.dto.UserRegisterParam;
import com.smart.prod.mbg.domain.entity.User;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface IUserService extends IService<User> {

    /**
     * 注册用户
     * @param userRegisterParam
     */
    User register(UserRegisterParam userRegisterParam);

    /**
     * 修改用户状态
     * @param id
     * @param status
     */
    void updateStatus(String id, Integer status);

    /**
     * 查询所有用户
     * @param criteria
     * @param page
     * @return
     */
    Map<String, Object> queryAll(UserCriteria criteria, Page page);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 新增用户
     * @param user
     */
    void create(UserCreateParam user);

    /**
     * 修改用户
     * @param user
     */
    void update(UserCreateParam user);

    /**
     * 修改密码
     * @param id        用户id
     * @param password  密码
     */
    void updatePassword(String id, String password);
}
