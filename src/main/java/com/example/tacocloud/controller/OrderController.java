package com.example.tacocloud.controller;

import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.dto.User;
import com.example.tacocloud.props.OrderProps;
import com.example.tacocloud.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepo;

    private OrderProps props;

    public OrderController(OrderRepository orderRepo,
                           OrderProps props){
        this.orderRepo = orderRepo;
        this.props = props;
    }


    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user,
                                Model model){
        Pageable pageable = PageRequest.of(0,props.getPageSize());
        model.addAttribute("orders", orderRepo.findByUserOrderByPlaceAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();

        if(errors.hasErrors())
            return "orderForm";

        order.setUser(user);
        orderRepo.save(order);

        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
