package com.unibuc.ro.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse extends RepresentationModel<ProductResponse> {
    private String name;

    private Long price;

    private String type;

    private String description;

    private String producer;

    private LocalDate producedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public LocalDate getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(LocalDate producedDate) {
        this.producedDate = producedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
