package com.hcltech.retail_ordering.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcltech.retail_ordering.entity.Menu;
import com.hcltech.retail_ordering.entity.Order;
import com.hcltech.retail_ordering.entity.User;
import com.hcltech.retail_ordering.repository.MenuRepository;
import com.hcltech.retail_ordering.repository.OrderRepository;
import com.hcltech.retail_ordering.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order placeOrder(Long menuId, Integer quantity, String username) {

        User user = userRepository.findByUsername(username).orElseThrow();
        Menu menu = menuRepository.findById(menuId).orElseThrow();

        if (menu.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        menu.setStock(menu.getStock() - quantity);

        Order order = Order.builder()
                .user(user)
                .menu(menu)
                .quantity(quantity)
                .totalAmount(menu.getPrice() * quantity)
                .orderDate(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }
}