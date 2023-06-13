package com.unibuc.ro.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
