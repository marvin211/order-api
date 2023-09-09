package com.devmarvin.orderapi.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fk_order", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "quantity", nullable = false)
    private Double quantity;
    @Column(name = "total", nullable = false)
    private Double total;

    public OrderLine() {
    }

    public OrderLine(Long id, Order order, Product product, Double price, Double quantity, Double total) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
