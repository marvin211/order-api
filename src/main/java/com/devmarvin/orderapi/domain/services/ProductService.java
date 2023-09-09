package com.devmarvin.orderapi.domain.services;

import com.devmarvin.orderapi.domain.entity.Product;
import com.devmarvin.orderapi.common.exceptions.GeneralServiceException;
import com.devmarvin.orderapi.common.exceptions.NoDataFoundException;
import com.devmarvin.orderapi.common.exceptions.ValidateServiceException;
import com.devmarvin.orderapi.domain.repository.ProductRepository;
import com.devmarvin.orderapi.domain.validators.ProductValidator;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductService.class);

    //Obtener todos los productos de la base de datos
    public List<Product> findAll(Pageable page){
        List<Product> products = productRepository.findAll(page).toList();
        return products;
    }

    // Obtener un producto por su id
    public Product findById( Long id){

        try {
            Product product = productRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            return product;
            
        } catch (ValidateServiceException | NoDataFoundException e) {
            logger.info(e.getMessage(), e);
            throw e;
            
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    // Guardar un producto en la base de datos o actualizarlo
    @Transactional
    public Product save(Product product){

        try {

            ProductValidator.save(product); // Validar el producto antes de guardar

            if(product.getId() == null){
                Product newProduct = productRepository.save(product);
                return newProduct;
            }

            Product existProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new NoDataFoundException("No existe el producto"));
            existProduct.setName(product.getName());
            existProduct.setPrice(product.getPrice());
            productRepository.save(existProduct);
            return existProduct;

        } catch (ValidateServiceException | NoDataFoundException e) {
            logger.info(e.getMessage(), e);
            throw e;
            
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    // Eliminar un producto de la base de datos
    @Transactional
    public void delete(Long productId){
        try{

            Product product = productRepository.findById(productId)
                    .orElseThrow( () -> new NoDataFoundException("No se encontró el producto"));

            productRepository.delete(product);

        }catch (ValidateServiceException | NoDataFoundException e) {
            logger.info(e.getMessage(), e);
            throw e;

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
