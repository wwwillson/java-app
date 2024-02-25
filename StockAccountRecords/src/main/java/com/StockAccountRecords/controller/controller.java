package com.StockAccountRecords.controller;

import com.StockAccountRecords.entity.User;
import com.StockAccountRecords.entity.Transaction;
import com.StockAccountRecords.service.TransactionService;
import com.StockAccountRecords.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/account"})
public class controller {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "login";
    }

    @PostMapping("/dashboard")
    public String dashboard(@ModelAttribute("user") User keyInUser,Model model) {
        User user = userService.findByUsername(keyInUser.getUsername());
        if(user != null && keyInUser.getPassword().equals(user.getPassword())){
            String username = user.getUsername();
            List<Transaction> transactions = transactionService.getTransactionsByUsername(username);

            model.addAttribute("user", user);
            model.addAttribute("transactions", transactions);

            return "dashboard";
        }
        else {
            return "login";
        }
    }
}