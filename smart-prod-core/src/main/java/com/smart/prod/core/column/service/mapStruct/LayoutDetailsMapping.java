package com.smart.prod.core.column.service.mapStruct;

import com.smart.prod.mbg.domain.dto.BaseMapping;
import com.smart.prod.core.column.entity.LayoutDetails;
import com.smart.prod.core.column.entity.dto.LayoutDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LayoutDetailsMapping extends BaseMapping<LayoutDetailsDTO, LayoutDetails> {

}