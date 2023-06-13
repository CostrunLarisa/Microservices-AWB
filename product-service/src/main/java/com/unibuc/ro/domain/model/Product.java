package com.unibuc.ro.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private Long id;

    private String name;

    private Long price;

    private String type;

    private String description;

    private String producer;

    private LocalDate producedDate;
}
