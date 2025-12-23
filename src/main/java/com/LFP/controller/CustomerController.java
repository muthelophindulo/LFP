package com.LFP.controller;

import com.LFP.model.customer;
import com.LFP.repository.CustomerRepository;
import com.LFP.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("customer", new customer());
        return "customer/form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("customer") customer customer
    ){
        if(customer.getCustomerNO() == null || customer.getCustomerNO().isEmpty()){
            customer.setCustomerNO("CUST-" + System.currentTimeMillis());
        }
        customer.setDateCreated(LocalDate.now());

        customerService.save(customer);
        return "redirect:/dashboard";
    }
}
