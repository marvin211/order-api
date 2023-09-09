package com.devmarvin.orderapi.domain.services;

import com.devmarvin.orderapi.domain.entity.Order;
import com.devmarvin.orderapi.domain.entity.OrderLine;
import com.devmarvin.orderapi.domain.entity.Product;
import com.devmarvin.orderapi.common.exceptions.GeneralServiceException;
import com.devmarvin.orderapi.common.exceptions.NoDataFoundException;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;
import com.devmarvin.orderapi.domain.repository.OrderLineRepository;
import com.devmarvin.orderapi.domain.repository.OrderRepository;
import com.devmarvin.orderapi.domain.repository.ProductRepository;
import com.devmarvin.orderapi.domain.validators.OrderValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderLineRepository orderLineRepository;
   @Autowired
   private ProductRepository productRepository;

    //Obtener todas las ordenes
    public List<Order> findAll(Pageable pageable){
        try{
            return orderRepository.findAll(pageable).toList();

        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    //Obtener una orden por su id
    public Order findById(Long id){
        try{
            return orderRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public Order save(Order order){
        try{

            OrderValidator.save(order);
            double totalAcumulado = 0;
            for (OrderLine line: order.getLines()){
                Product product = productRepository.findById(line.getProduct().getId())
                        .orElseThrow( () -> new NoDataFoundException("No existe el producto con el id "+ line.getProduct().getId()));

                line.setPrice(product.getPrice());
                line.setTotal(product.getPrice() * line.getQuantity());
                totalAcumulado += line.getTotal();
            }

            order.setTotal(totalAcumulado);

            //Indicarle a que orden pertenecen las lineas
            order.getLines().forEach(line -> line.setOrder(order));

            if(order.getId() == null){
                order.setRegDate(LocalDateTime.now());
                return orderRepository.save(order);
            }

            //Actualizar orden
            Order searchOrder = orderRepository.findById(order.getId())
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

            //Si la orden existe
            order.setRegDate(searchOrder.getRegDate());

            //Eliminacion manual de las lineas que no se actualizaron
            Set<Long> updatedLineIds = order.getLines().stream()
                    .map(OrderLine::getId)
                    .collect(Collectors.toSet());

            List<OrderLine> linesToDelete = searchOrder.getLines().stream()
                    .filter(line -> !updatedLineIds.contains(line.getId()))
                    .collect(Collectors.toList());

            orderLineRepository.deleteAll(linesToDelete); //Se eliminan las líneas que deben eliminarse.
            return orderRepository.save(order);

        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    //Eliminar una orden
    @Transactional
    public void delete(Long id){
        try{
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));
            //Eliminamos la orden si fue encontrada
            orderRepository.delete(order);

        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
