package com.LFP.controller;

import com.LFP.service.MeasurementService;
import com.LFP.service.ProductService;
import com.LFP.service.todoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.LFP.service.OrderService;

@Controller
public class WebController {
    private final OrderService orderService;
    private final ProductService productService;
    private final MeasurementService measurementService;
    private final todoService todoService;
    public WebController(OrderService orderService, ProductService productService, MeasurementService measurementService, todoService todoService) {
        this.orderService = orderService;
        this.productService = productService;
        this.measurementService = measurementService;
        this.todoService = todoService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping
    public String login2(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        model.addAttribute("orderCount",orderService.getAllOrders().size());
        model.addAttribute("revenue",productService.revenue());
        model.addAttribute("productCount",productService.getProducts().size());
        model.addAttribute("measurementCount",measurementService.getMeasurements().size());
        model.addAttribute("todoCount",todoService.getTodos().size());
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
}
