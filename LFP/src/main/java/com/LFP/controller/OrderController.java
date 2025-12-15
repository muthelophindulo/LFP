package com.LFP.controller;

import com.LFP.model.customer;
import com.LFP.model.order;
import com.LFP.model.product;
import com.LFP.service.CustomerService;
import com.LFP.service.OrderService;
import com.LFP.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;

    public OrderController(OrderService orderService, ProductService productService, CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
    }

    @GetMapping("/new")
    public String createOrderForm(Model model) {
        order order = new order();
        order.setDate(LocalDate.now());


        List<product> products = productService.getProducts();
        List<customer> customers = customerService.getCustomers();

        model.addAttribute("order", order);
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);

        model.addAttribute("pageTitle", "Create New Order");
        model.addAttribute("action", "Create");

        return "orders/form";
    }

    // 2. Save new order (POST request)
    @PostMapping("/save")
    public String saveOrder(
            @ModelAttribute("order") order order,
            RedirectAttributes redirectAttributes) {

        if (order.getOrderNo() == null || order.getOrderNo().isEmpty()) {
            order.setOrderNo("ORD-" + System.currentTimeMillis());
        }

        if (order.getDate() == null || order.getDate().toString().isEmpty()) {
            order.setDate(LocalDate.now());
        }

        // Save the order
        order savedOrder = orderService.Save(order);

        System.out.println(order.getProductName());

        redirectAttributes.addFlashAttribute("success",
                "Order #" + savedOrder.getOrderNo() + " created successfully!");

        return "redirect:/dashboard";
    }

    @GetMapping("/list")
    public String listOrders(Model model) {
        List<order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        // Add statistics
        model.addAttribute("totalOrders", orderService.getAllOrders().size());
        model.addAttribute("totalQuantity", orderService.getTotalQuantity());
        model.addAttribute("todayOrders", orderService.getTodayOrders().size());
        model.addAttribute("monthOrders", orderService.getThisMonthOrderCount());

        return "orders/orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        orderService.delete(id);

        return "redirect:/orders/list";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model){
        order updatedOrder = orderService.getOrderByID(id);

        List<product> products = productService.getProducts();
        List<customer> customers = customerService.getCustomers();

        model.addAttribute("order", updatedOrder);
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);

        model.addAttribute("pageTitle", "Create New Order");

        return "orders/update";
    }

    @PostMapping("/update")
    public String saveUpdate(
            @ModelAttribute order order
    ){
        order.setOrderNo(orderService.getOrderByID(order.getId()).getOrderNo());
        order.setDate( orderService.getOrderByID( order.getId()).getDate());
        orderService.update(order);
        return "redirect:/orders/list";
    }

    @GetMapping("/view/{id}")
    public String View(@PathVariable Long id,Model model){
        order x = orderService.getOrderByID(id);

        model.addAttribute("order",x);

        return "orders/view";
    }

}
