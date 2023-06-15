package com.unibuc.clientservice.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;


@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Client extends RepresentationModel<Client> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email cannot be null")
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "LastName cannot be null")
    @NotEmpty(message = "LastName cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "FirstName cannot be null")
    @NotEmpty(message = "FirstName cannot be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\d{10}$")
    private String phoneNumber;
}
