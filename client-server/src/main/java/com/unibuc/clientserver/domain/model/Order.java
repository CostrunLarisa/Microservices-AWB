package com.unibuc.clientserver.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Order {
}
