package com.esisba.deliveryservice.controller;

import com.esisba.deliveryservice.entitiy.Delivery;
import com.esisba.deliveryservice.entitiy.Order;
import com.esisba.deliveryservice.repository.DeliveryRepository;
import com.esisba.deliveryservice.repository.OrderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api-v1/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrderProxy orderProxy;
    @GetMapping("/")
    public Collection<Delivery> getAllDelivery(){
        return deliveryRepository.findAll();
    }

    @GetMapping("/{idDelivery}")
    public Delivery getDelivery(@PathVariable("idDelivery") Long idDelivery){
        return deliveryRepository.findById(idDelivery).get();
    }

    @PostMapping("/")
    public Delivery createDelivery(@RequestBody Delivery delivery){
        Delivery d = delivery;
        int i = 020;
        return deliveryRepository.save(delivery);
    }

    @PutMapping("{idDelivery}")
    public Delivery updateDelivery(@PathVariable("idDelivery") Long idDelivery, @Valid @RequestBody Delivery delivery){
        if(deliveryRepository.findById(idDelivery).isPresent()){
            delivery.setIdDelivery(idDelivery);
            return deliveryRepository.save(delivery);
        }
        return null;
    }

    @DeleteMapping("{idDelivery}")
    public void deleteDelivery(@PathVariable("idDelivery") Long idDelivery){
        if(deliveryRepository.findById(idDelivery).isPresent()){
            deliveryRepository.deleteById(idDelivery);
        }
    }

    @GetMapping("{idDelivery}/order")
    public Order getOrder(@PathVariable("idDelivery") Long idDelivery){
        Delivery delivery = deliveryRepository.findDeliveryByOrderId(idDelivery);
        System.out.println(delivery.getIdDelivery());
        return orderProxy.getOrderById(delivery.getOrderId());
    }
}