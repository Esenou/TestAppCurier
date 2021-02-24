package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.enums.OrderStatus;
import com.example.demo.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {
    Long addOrder(OrderModel orderModel);

    List<Order> findAll();

    void deleteOrder(Long id);

    void updateOrder(Long id, OrderModel orderModel);

    Order findById(Long id);

    OrderModel getOrderModel(Long id);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findAllByProduct(String search, Pageable pageable);

    List<Order> findByProduct(String name);

    Page<Order> findAllByCourierId(Long id,String productName,Pageable pageable);

    Page<Order> findByCourierId(Long id, OrderStatus status, Pageable pageable);

    Boolean updateStatus(Long id);
}
