package com.esisba.webservice.entitiy;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Delivery {

    private Long idDelivery;

    private String rue;

    private int numero;

    private String codePostal;

    private String ville;

    private Long orderId;
}
