package com.unibuc.ro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product extends RepresentationModel<Product> {
    @Id
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Long price;

    private String type;

    private String description;
    @NonNull
    private String producer;
    @NonNull
    private LocalDate producedDate;

    public Product() {

    }
}
