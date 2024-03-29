package com.smart.prod.mbg.domain.vo;

import cn.hutool.core.collection.CollUtil;
import com.smart.prod.common.constanst.Constanst;
import com.smart.prod.mbg.domain.dto.MenuNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 用户详情
 * @Author: Guoji Shen
 * @Date: 2023/6/30 08:53
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements Serializable {

    @Schema(description = "用户id")
    private String id;

    @Schema(description = "用户姓名")
    private String username;

    @Schema(description = "头像")
    private String icon;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "是否为管理员")
    private boolean admin;

    @Schema(description = "权限")
    private List<String> permission;

    @Schema(description = "角色")
    private List<String> roleList;

    @Schema(description = "菜单")
    private List<MenuNode> menuList;

    public boolean isAdmin() {
        if (CollUtil.isNotEmpty(roleList)) {
            for (String role : roleList) {
                if (Constanst.ADMIN.equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }
}
