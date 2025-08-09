package com.mddApi.controllers;

import com.mddApi.dto.SubscriptionDTO;
import com.mddApi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionDTO create(@RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.create(subscriptionDTO);
    }

    @GetMapping("/user/{userId}")
    public List<SubscriptionDTO> getByUser(@PathVariable Long userId) {
        return subscriptionService.getByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        subscriptionService.delete(id);
    }
}
