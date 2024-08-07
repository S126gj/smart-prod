package com.smart.prod.core.column.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smart.prod.common.constanst.CacheKey;
import com.smart.prod.common.utils.RedisUtils;
import com.smart.prod.common.utils.Str;
import com.smart.prod.mbg.auth.util.StpUserUtil;
import com.smart.prod.mbg.domain.entity.Role;
import com.smart.prod.mbg.service.IRoleMenuService;
import com.smart.prod.core.column.entity.Layout;
import com.smart.prod.core.column.entity.dto.LayoutDTO;
import com.smart.prod.core.column.entity.dto.LayoutDetailsDTO;
import com.smart.prod.core.column.mapper.LayoutDetailsMapper;
import com.smart.prod.core.column.mapper.LayoutMapper;
import com.smart.prod.core.column.service.LayoutDetailsService;
import com.smart.prod.core.column.service.LayoutService;
import com.smart.prod.core.column.service.mapStruct.LayoutDetailsMapping;
import com.smart.prod.core.column.service.mapStruct.LayoutMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 显示列表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2020-07-28
 */
@RequiredArgsConstructor
@Service
@CacheConfig(cacheNames = CacheKey.LAYOUT)
public class LayoutServiceImpl extends ServiceImpl<LayoutMapper, Layout> implements LayoutService {

    private final LayoutMapping layoutMapping;
    private final LayoutDetailsService layoutDetailsService;
    private final LayoutDetailsMapping layoutDetailsMapping;
    private final LayoutDetailsMapper layoutDetailsMapper;
    private final RedisUtils redisUtils;
    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    // @Cacheable(key = "#root.target.getKey()")
    public List<LayoutDTO> select() {
        List<LayoutDTO> layoutDTOs = layoutMapping.toDto(baseMapper.selectList(new QueryWrapper()));
        List<LayoutDetailsDTO> layoutDetailsDTOs = layoutDetailsService.queryAllNoDocId();
        addLayout(layoutDTOs, layoutDetailsDTOs);
        for (LayoutDTO LayoutDTO : layoutDTOs) {
            List<LayoutDetailsDTO> LayoutDetailsDTOList = LayoutDTO.getLayoutDetailsDTOs();
            LayoutDetailsDTO LayoutDetailsDTO = new LayoutDetailsDTO();
            Collections.sort(LayoutDetailsDTOList, LayoutDetailsDTO);
        }
        return layoutDTOs;
    }


    public void parse(LayoutDetailsDTO detailsDto) {
        // List<Role> roleList = roleMenuService.getRoleListByUserId(StpUserUtil.getLoginId().toString());
        // // 根据角色自定义按钮的权限
        // if (Str.isNotBlank(detailsDto.getAuthConfig())) {
        //     for (Role role : roleList) {
        //         String[] split = detailsDto.getAuthConfig().split(",");
        //         for (int i = 0; i < split.length; i++) {
        //             if (role.getId().equals(split[i])) {
        //                 detailsDto.setVisible(true);
        //             } else {
        //                 detailsDto.setVisible(false);
        //             }
        //         }
        //     }
        // }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LayoutDTO dto) {
        Layout entity = layoutMapping.toEntity(dto);
        this.saveOrUpdate(entity);
        dto.setId(entity.getId());
        layoutDetailsService.save(dto);
        redisUtils.del(String.format("%s%s", CacheKey.LAYOUT, getKey()));
    }

    @Override
    public LayoutDTO findById(String id) {
        LayoutDTO layoutDTO = layoutMapping.toDto(baseMapper.selectById(id));
        List<LayoutDetailsDTO> detailsDtos = layoutDetailsMapping.toDto(layoutDetailsMapper.findByLayoutId(id));
        layoutDTO.getLayoutDetailsDTOs().addAll(detailsDtos);
        for (LayoutDetailsDTO detailsDto : detailsDtos) {
            parse(detailsDto);
        }
        return layoutDTO;
    }

    private void addLayout(List<LayoutDTO> layoutDTOs, List<LayoutDetailsDTO> layoutDetailsDTOs) {
        for (LayoutDTO layoutDTO : layoutDTOs) {
            for (LayoutDetailsDTO detailsDto : layoutDetailsDTOs) {
                if (layoutDTO.getId().equals(detailsDto.getLayoutId())) {
                    layoutDTO.getLayoutDetailsDTOs().add(detailsDto);
                    parse(detailsDto);
                }
            }
        }
    }

    public String getKey(){
        return  String.format("%s:", StpUserUtil.getLoginId());
    }
}
