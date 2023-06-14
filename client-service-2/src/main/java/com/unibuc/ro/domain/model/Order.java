package com.unibuc.ro.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Order extends RepresentationModel<Order> {
    @Id
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Client client;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
