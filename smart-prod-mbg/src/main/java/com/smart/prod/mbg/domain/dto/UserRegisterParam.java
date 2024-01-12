package com.smart.prod.mbg.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 13:07
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "用户注册参数")
public class UserRegisterParam {

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别")
    private Integer gender;

    @Schema(description = "手机号")
    private String phone;
}