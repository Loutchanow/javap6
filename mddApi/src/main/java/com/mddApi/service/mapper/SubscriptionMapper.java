package com.mddApi.service.mapper;

import com.mddApi.dto.SubscriptionDTO;
import com.mddApi.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface SubscriptionMapper {
	Subscription toEntity(SubscriptionDTO subscriptionDTO);
	SubscriptionDTO toDto(Subscription subscription);

    void copy(SubscriptionDTO subscriptionDTO, @MappingTarget Subscription subscription);

}
