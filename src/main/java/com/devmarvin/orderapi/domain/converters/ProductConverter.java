package com.devmarvin.orderapi.domain.converters;

import com.devmarvin.orderapi.presentation.dto.ProductDTO;
import com.devmarvin.orderapi.domain.entity.Product;

public class ProductConverter extends AbstractConverter<Product, ProductDTO>{

    @Override
    public ProductDTO fromEntity(Product entity) { //Convertir de entidad a dto
        if(entity == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        
        return dto;
    }

    @Override
    public Product fromDTO(ProductDTO dto) { //Convertir de dto a entidad

        if(dto == null) return null;

        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        
        return entity;
    }
}
