package com.esisba.deliveryservice.entitiy;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

    private String rue;

    private int numero;

    @Column(name = "code_postal")
    private String codePostal;

    private String ville;

    private Long orderId;
}
