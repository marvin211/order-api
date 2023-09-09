package com.devmarvin.orderapi.presentation.controllers;

import com.devmarvin.orderapi.domain.converters.ProductConverter;
import com.devmarvin.orderapi.presentation.dto.ProductDTO;
import com.devmarvin.orderapi.domain.entity.Product;
import com.devmarvin.orderapi.domain.services.ProductService;
import com.devmarvin.orderapi.common.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductConverter productConverter;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> findAll(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize
    ){
        Pageable page = PageRequest.of(pageNumber, pageSize);

        List<Product> products = productService.findAll(page);
        List<ProductDTO> productsDTO = productConverter.fromEntity(products);

        return new WrapperResponse(true, "success", productsDTO)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable("productId") Long productId){

        Product product = productService.findById(productId);
        ProductDTO productDTO = productConverter.fromEntity(product);

        return new WrapperResponse<ProductDTO>(true, "success", productDTO)
                .createResponse(HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product){
        Product newProduct = productService.save(productConverter.fromDTO(product));

        ProductDTO productDTO = productConverter.fromEntity(newProduct);

        return new WrapperResponse(true, "success", productDTO)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product){
        Product updateProduct = productService.save(productConverter.fromDTO(product));

        ProductDTO productDTO = productConverter.fromEntity(updateProduct);

        return new WrapperResponse(true, "success", productDTO)
                .createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> delete(@PathVariable("productId") Long productId){
        productService.delete(productId);

        return new WrapperResponse(true, "success", null)
                .createResponse(HttpStatus.OK);
    }

}
