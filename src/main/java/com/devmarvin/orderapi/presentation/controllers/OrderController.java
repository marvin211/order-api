package com.devmarvin.orderapi.presentation.controllers;

import com.devmarvin.orderapi.domain.converters.OrderConverter;
import com.devmarvin.orderapi.presentation.dto.OrderDTO;
import com.devmarvin.orderapi.domain.entity.Order;
import com.devmarvin.orderapi.domain.services.OrderService;
import com.devmarvin.orderapi.common.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderConverter orderConverter;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<WrapperResponse <List<OrderDTO>>> findAll(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize){

        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Order> orders = orderService.findAll(page);

        return new WrapperResponse<>(true, "success", orderConverter.fromEntity(orders))
                .createResponse();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findByID(@PathVariable("id") Long id){
        Order order = orderService.findById(id);
        return new WrapperResponse<>(true, "success", orderConverter.fromEntity(order))
                .createResponse();
    }

    @PostMapping("/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO order){
        Order newOrder = orderService.save(orderConverter.fromDTO(order));
        return new WrapperResponse<>(true, "success", orderConverter.fromEntity(newOrder))
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO order){
        Order updateOrder = orderService.save(orderConverter.fromDTO(order));
        return new WrapperResponse<>(true, "success", orderConverter.fromEntity(updateOrder))
                .createResponse();
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> delete(@PathVariable("orderId") Long orderId){
        orderService.delete(orderId);

        return new WrapperResponse<>(true, "success", null)
                .createResponse();
    }

}
