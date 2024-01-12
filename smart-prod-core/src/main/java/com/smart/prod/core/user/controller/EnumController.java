package com.smart.prod.core.user.controller;

import cn.hutool.core.util.EnumUtil;
import com.smart.prod.common.utils.R;
import com.smart.prod.file.entity.enums.FileResouceTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/4 15:36
 */
@RestController
@RequestMapping("/enum")
@Tag(name = "枚举")
public class EnumController {

    @Operation(summary = "文件来源类型")
    @GetMapping("/fileResource")
    public R fileResource(){
        return R.ok().data(EnumUtil.getNameFieldMap(FileResouceTypeEnum.class, "msg"));
    }
}