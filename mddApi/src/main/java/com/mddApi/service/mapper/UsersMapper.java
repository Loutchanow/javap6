package com.mddApi.service.mapper;

import com.mddApi.dto.UsersDTO;
import com.mddApi.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UsersMapper {
    Users toEntity(UsersDTO usersDTO);
    UsersDTO toDto(Users users);

    void copy(UsersDTO usersDTO, @MappingTarget Users users);

}
