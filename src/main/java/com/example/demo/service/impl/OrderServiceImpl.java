package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.enums.OrderStatus;
import com.example.demo.model.OrderModel;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Long addOrder(OrderModel orderModel) {
        Order order = new Order();
        //order.setOrdersStatus(orderModel.getOrdersStatus());
        order.setUser(userService.findById(orderModel.getUser_id()));
        order.setCourierId(userService.findById(orderModel.getUser_id()));
        order.setProduct(orderModel.getProduct());
        order.setTarget_location(orderModel.getTarget_location());
        return orderRepository.save(order).getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void updateOrder(Long id, OrderModel orderModel) {
        Order order = findById(id);
        order.setProduct(orderModel.getProduct());
        order.setTarget_location(orderModel.getTarget_location());
        order.setCourierId(userService.findById(orderModel.getCourier_id()));
        orderRepository.save(order).getId();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!!"));
    }

    @Override
    public OrderModel getOrderModel(Long id) {
        Order order = findById(id);
        return OrderModel.builder()
                .id(order.getId())
                .courier_id(order.getCourierId().getId())
                .product(order.getProduct())
                .target_location(order.getTarget_location()).build();
    }

    @Override
    public List<Order> findByProduct(String name) {
        return orderRepository.findByProduct(name);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findAllByCourierId(Long id, String productName, Pageable pageable) {
        return orderRepository.findAllByProductLikeAndCourierId_Id(productName, id, pageable);
    }


    @Override
    public Page<Order> findByCourierId(Long id, OrderStatus status, Pageable pageable) {
        return orderRepository.findAllByCourierId_IdAndOrdersStatus(id, status, pageable);
    }

    @Override
    public Boolean updateStatus(Long id) {
        Order order = findById(id);
        order.setOrdersStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
        return true;
    }

    @Override
    public Page<Order> findAllByProduct(String search, Pageable pageable) {
        return orderRepository.findAllByProduct(search, pageable);
    }

}
