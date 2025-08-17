package com.mddApi.service.mapper;

import com.mddApi.dto.SubscriptionDTO;
import com.mddApi.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface SubscriptionMapper {
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "subject.id", source = "subjectId")
	Subscription toEntity(SubscriptionDTO subscriptionDTO);
    
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "subject.id", target = "subjectId")
	SubscriptionDTO toDto(Subscription subscription);

    void copy(SubscriptionDTO subscriptionDTO, @MappingTarget Subscription subscription);

}
