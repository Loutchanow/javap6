package com.mddApi.service.interfaces;
import com.mddApi.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {

     SubscriptionDTO create(SubscriptionDTO subscriptionDTO);

     List<SubscriptionDTO> getByUserId(Long userId);
    
     void delete(Long id);
}
