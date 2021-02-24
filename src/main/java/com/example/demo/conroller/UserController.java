package com.example.demo.conroller;

import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String index(Model model) {
        model.addAttribute("user", new UserModel());
        model.addAttribute("roles", Role.values());
        return "newUser";
    }

    @PostMapping("/new") // Map ONLY POST Requests
    public String addNewUser(@ModelAttribute("user") UserModel userModel, Model model) {
        userService.addUser(userModel);
        return "redirect:/user/all";
    }

    @GetMapping(path = "/all")
    String getAllUsers(Model model, @RequestParam(value = "searchResult", required = false) String username, @PageableDefault(3) Pageable pageable) {
        Page<User> users = username != null ?
                userService.findAllByName(username.toLowerCase(), pageable)
                : userService.findAll(pageable);

        model.addAttribute("userList", userService.findByName(username));
        model.addAttribute("userList", users);
        model.addAttribute("isEmpty", users.isEmpty());
        model.addAttribute("searchResult", username);
        return "userList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id,
                       Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", userService.userModel(user));
        model.addAttribute("roles", Role.values());
        model.addAttribute("status", UserStatus.values());
        return "user";
    }

    @PostMapping("/update/{id}") // Map ONLY POST Requests
    public String updateUser(@ModelAttribute("user") UserModel userModel, @PathVariable("id") Long id, Model model) {
        userService.updateUser(id, userModel);
        return "redirect:/user/all";
    }

    @PostMapping("/delete/{id}") // Map ONLY POST Requests
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/user/all";
    }
}
