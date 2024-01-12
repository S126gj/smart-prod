package com.smart.prod.core.column.service.impl;

import com.smart.prod.mbg.domain.entity.Role;
import com.smart.prod.mbg.service.IRoleService;
import com.smart.prod.core.column.entity.dto.ComboDTO;
import com.smart.prod.core.column.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Guoji Shen
 * @Date: 2023/7/3 11:06
 */
@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    private IRoleService roleService;

    @Override
    public ComboDTO combo(Object key, String name, String value, String label) {
        return ComboDTO.builder()
            .key(key)
            .name(name)
            .value(value)
            .label(label)
            .build();
    }

    @Override
    public List<ComboDTO> getRole() {
        List<ComboDTO> comboDTOS = new ArrayList<>();
        List<Role> roles = roleService.queryAllCache();
        roles.forEach(role -> comboDTOS.add(combo(role.getId(), role.getName(), role.getId(), role.getName())));
        return comboDTOS;
    }
}