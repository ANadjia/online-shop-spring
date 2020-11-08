package com.esisba.webservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {


    private Long idOrderItem;

    private Integer qte;

    private Long productId;


    private Long orderId;
}