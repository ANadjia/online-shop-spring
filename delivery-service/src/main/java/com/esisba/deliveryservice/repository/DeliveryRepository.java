package com.esisba.deliveryservice.repository;

import com.esisba.deliveryservice.entitiy.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findDeliveryByOrderId(Long id);
}