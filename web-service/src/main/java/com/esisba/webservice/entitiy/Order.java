package com.esisba.webservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    private Long idOrder;

    private Long userId;

    private Set<OrderItem> orderItems;

}