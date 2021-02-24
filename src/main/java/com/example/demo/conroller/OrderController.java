package com.example.demo.conroller;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.Role;
import com.example.demo.model.OrderModel;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("order/")
public class OrderController {

    private final OrderService orderService;
    public final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String index(Model model) {
        model.addAttribute("order", new OrderModel());
        model.addAttribute("users", userService.findAllByRole(Role.User));
        return "newOrder";
    }

    @PostMapping("/new")
    public String addNewOrder(@ModelAttribute("order") OrderModel orderModel, Model model) {
        orderService.addOrder(orderModel);
        return "redirect:/order/all";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id,
                       Model model) {
        model.addAttribute("order", orderService.getOrderModel(id));
        model.addAttribute("users", userService.findAllByRole(Role.User));
        return "order";
    }

    @PostMapping("/update/{id}") // Map ONLY POST Requests
    public String updateUser(@ModelAttribute("order") OrderModel orderModel, @PathVariable("id") Long id, Model model) {
        orderService.updateOrder(id, orderModel);
        return "redirect:/order/all";
    }

    @GetMapping(path = "/all")
    String getAllOrder(Model model, @RequestParam(value = "searchResult", required = false) String productName, @PageableDefault(3) Pageable pageable) {
        Page<Order> orders;
        // if search
        if (productName != null) {
            orders = orderService.findAllByProduct(productName.toLowerCase(Locale.ROOT), pageable);
        } else {
            orders = orderService.findAll(pageable);
        }

        boolean isEmpty = orders.isEmpty();
        model.addAttribute("orderList", orders);
        model.addAttribute("isEmpty", isEmpty);
        model.addAttribute("searchResult", productName);
        return "orderList";
    }

    @PostMapping("/delete/{id}")
    String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/order/all";
    }

    @GetMapping(path = "/orderCourier")
    String getOrderUserAll(Model model,
                           @RequestParam(value = "searchResult", required = false) String productName,
                           @PageableDefault(3) Pageable pageable,
                           Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Page<Order> orders;
        // if search
        if (productName != null) {
            orders = orderService.findAllByCourierId(user.getId(), productName.toLowerCase(Locale.ROOT), pageable);
        } else {
            orders = orderService.findByCourierId(user.getId(), OrderStatus.AWAITING, pageable);
        }
        model.addAttribute("orderList", orders);
        return "orderCourier";
    }
    @GetMapping(path = "/orderCourier/delivered")
    String getDeliveredOrder(Model model,
                           @RequestParam(value = "searchResult", required = false) String productName,
                           @PageableDefault(3) Pageable pageable,
                           Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Page<Order> orders;
        // if search
        if (productName != null) {
            orders = orderService.findAllByCourierId(user.getId(), productName.toLowerCase(Locale.ROOT), pageable);
        } else {
            orders = orderService.findByCourierId(user.getId(), OrderStatus.DELIVERED, pageable);
        }
        model.addAttribute("orderList", orders);
        return "orderCourier";
    }

    @GetMapping("/change/{userId}/{id}")
    String changeStatusOrder(@PathVariable("userId") Long userId, @PathVariable("id") Long id) {
        orderService.updateStatus(id);
        return "redirect:/order/orderCourier";
    }




}
