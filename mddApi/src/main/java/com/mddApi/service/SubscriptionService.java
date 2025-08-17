package com.mddApi.service;

import com.mddApi.dto.SubscriptionDTO;
import com.mddApi.model.Subject;
import com.mddApi.model.Subscription;
import com.mddApi.model.Users;
import com.mddApi.repository.SubscriptionRepository;
import com.mddApi.service.mapper.SubscriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    public SubscriptionDTO create(SubscriptionDTO subscriptionDTO) {
//    	Users user = new Users();
//    	user.setId(subscriptionDTO.getUserId());
//    	Subject subject = new Subject();
//    	subject.setId(subscriptionDTO.getSubjectId());
    			
    	
        Subscription subscription = subscriptionMapper.toEntity(subscriptionDTO);
        
        Subscription saved = subscriptionRepository.save(subscription);
        return subscriptionMapper.toDto(saved);
    }

    public List<SubscriptionDTO> getByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId)
                .stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
