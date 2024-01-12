package com.smart.prod.core.column.service.mapStruct;

import com.smart.prod.mbg.domain.dto.BaseMapping;
import com.smart.prod.core.column.entity.Layout;
import com.smart.prod.core.column.entity.dto.LayoutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LayoutMapping extends BaseMapping<LayoutDTO, Layout> {

}
