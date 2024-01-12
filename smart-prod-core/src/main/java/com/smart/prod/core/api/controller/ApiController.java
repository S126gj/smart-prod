package com.smart.prod.core.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * satoken签名对接，具体可查看
 * https://sa-token.cc/doc.html#/plugin/api-sign
 * 需要注意的是，/api下的所有接口均需要放开权限校验
 * @Author: Guoji Shen
 * @Date: 2023/8/9 15:10
 */
@RestController
@RequestMapping("/api/open")
@Tag(name = "对接开放接口")
@Slf4j
public class ApiController {
}