package com.smart.prod.mbg.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smart.prod.mbg.domain.criteria.UserCriteria;
import com.smart.prod.mbg.domain.entity.User;
import com.smart.prod.mbg.domain.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author GuojiShen
 * @since 2023-06-27
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询所有用户
     * @param criteria
     * @param page
     * @return
     */
    IPage<UserVO> queryAll(@Param("criteria") UserCriteria criteria, Page page);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User findById(@Param("id") String id);
}
