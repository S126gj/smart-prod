package com.smart.prod.core.column.controller;

import com.smart.prod.common.utils.R;
import com.smart.prod.core.column.service.ComboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 11:05
 */
@RestController
@RequestMapping("/combo/layout")
@RequiredArgsConstructor
@Tag(name = "布局")
public class ComboController {

    @Autowired
    private ComboService comboService;

    @Operation(summary = "获取角色")
    @GetMapping("/getRole")
    public R getRole() {
        return R.ok().data(comboService.getRole());
    }

}