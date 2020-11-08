package com.esisba.orderservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;

    private Integer qte;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private Order order;
}