package com.devmarvin.orderapi.presentation.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private String regData;
    private List<OrderLineDTO> lines;
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegData() {
        return regData;
    }

    public void setRegData(String regData) {
        this.regData = regData;
    }

    public List<OrderLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<OrderLineDTO> lines) {
        this.lines = lines;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
