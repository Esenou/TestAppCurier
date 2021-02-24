package com.example.demo.model;


import com.example.demo.entity.User;
import com.example.demo.enums.OrderStatus;
import jdk.jshell.Snippet;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel {
    private Long id;
    private OrderStatus ordersStatus;
    private Long user_id;
    private String product;
    private String target_location;
    private Long courier_id;




}
