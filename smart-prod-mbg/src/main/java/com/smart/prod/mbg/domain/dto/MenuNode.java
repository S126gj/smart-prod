package com.smart.prod.mbg.domain.dto;

import com.smart.prod.mbg.domain.entity.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/6/28 13:36
 */
@Getter
@Setter
@Schema(description = "菜单树状节点")
public class MenuNode extends Menu {

    @Schema(description = "子级菜单")
    private List<MenuNode> children;
}