package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//OrderStatus ordersStatus, Long user_id, String product, String target_location, Long courier_id
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select ordr FROM  Order ordr where ordr.product LIKE %?1%")
    Page<Order> findAllByProduct(String product, Pageable pageable);


    List<Order> findByProduct(String productName);

//    @Query(value = "select ordr FROM  Order ordr where ordr.courierId.id=:id")
//    Page<Order>findByCourierId(Long id,Pageable pageable);

    Page<Order> findAllByCourierId_IdAndOrdersStatus(Long id, OrderStatus status, Pageable pageable);

    //@Query(value = "select ord FROM  Order ord where ord.courierId.id=:id and ord.product LIKE %?1%")
    Page<Order> findAllByCourierId(Long id,String product, Pageable pageable);

    Page<Order> findAllByProductLikeAndCourierId_Id(String product, Long id, Pageable pageable);

}
