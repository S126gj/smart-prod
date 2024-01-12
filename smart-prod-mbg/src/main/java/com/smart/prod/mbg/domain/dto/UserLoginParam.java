package com.smart.prod.mbg.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 17:33
 */
@Getter
@Setter
@Schema(description = "用户登录参数")
public class UserLoginParam {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

}
