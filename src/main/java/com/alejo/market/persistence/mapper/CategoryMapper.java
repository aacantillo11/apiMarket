package com.alejo.market.persistence.mapper;

import com.alejo.market.domain.Category;
import com.alejo.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(source = "id",target = "categoryId")
    @Mapping(source = "descripcion",target = "category")
    @Mapping(source = "estado",target = "active")
    Category categoriaToCategory(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos",ignore = true)
    Categoria categoryToCategoria(Category category);
}
