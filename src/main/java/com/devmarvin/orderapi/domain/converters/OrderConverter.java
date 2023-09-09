
package com.devmarvin.orderapi.domain.converters;

import com.devmarvin.orderapi.presentation.dto.OrderDTO;
import com.devmarvin.orderapi.presentation.dto.OrderLineDTO;
import com.devmarvin.orderapi.domain.entity.Order;
import com.devmarvin.orderapi.domain.entity.OrderLine;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConverter extends AbstractConverter<Order, OrderDTO>{

    private final DateTimeFormatter dateTimeFormat;
    private final ProductConverter productConverter;

    public OrderConverter(DateTimeFormatter dateTimeFormat, ProductConverter productConverter){
        this.dateTimeFormat = dateTimeFormat;
        this.productConverter = productConverter;
    }

    @Override
    public OrderDTO fromEntity(Order entity) {
        if(entity == null) return null; //Si la entidad es nula simplemente retornamos null

        //Lista de tipo dto
        List<OrderLineDTO> lines = fromOrderLineEntity(entity.getLines());

       OrderDTO dto = new OrderDTO();
       dto.setId(entity.getId());
       dto.setLines(lines);
       dto.setRegData(entity.getRegDate().format(dateTimeFormat));
       dto.setTotal(entity.getTotal());

       return dto;
    }

    @Override
    public Order fromDTO(OrderDTO dto) {
        if(dto == null) return null;

        List<OrderLine> lines = fromOrderLineDTO(dto.getLines());

        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setLines(lines);
        entity.setTotal(dto.getTotal());

        return entity;
    }

    //Metodos para convertir las lineas
    private List<OrderLineDTO> fromOrderLineEntity(List<OrderLine> lines){
        if(lines == null) return null;

        return lines.stream().map( line -> {
            OrderLineDTO orderLineDTO = new OrderLineDTO();
            orderLineDTO.setId(line.getId());
            orderLineDTO.setPrice(line.getPrice());
            orderLineDTO.setProduct(productConverter.fromEntity(line.getProduct()));
            orderLineDTO.setQuantity(line.getQuantity());
            orderLineDTO.setTotal(line.getTotal());
            return orderLineDTO;
        }).collect(Collectors.toList());
    }

    private List<OrderLine> fromOrderLineDTO(List<OrderLineDTO> lines){
        if(lines == null) return null;

        return lines.stream().map( line -> {
            OrderLine orderLineEntity = new OrderLine();
            orderLineEntity.setId(line.getId());
            orderLineEntity.setPrice(line.getPrice());
            orderLineEntity.setProduct(productConverter.fromDTO(line.getProduct()));
            orderLineEntity.setQuantity(line.getQuantity());
            orderLineEntity.setTotal(line.getTotal());
            return orderLineEntity;
        }).collect(Collectors.toList());
    }
}
