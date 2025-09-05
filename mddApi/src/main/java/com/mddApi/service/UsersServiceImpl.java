package com.mddApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mddApi.dto.UserResponseDTO;
import com.mddApi.dto.UsersDTO;
import com.mddApi.model.Users;
import com.mddApi.repository.UsersRepository;
import com.mddApi.service.interfaces.UsersService;
import com.mddApi.service.mapper.UsersMapper;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepository userRepository;
	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public UsersDTO save(UsersDTO usersDTO) {
		
		Users user = usersMapper.toEntity(usersDTO);
		Users savedUser = userRepository.save(user);
		   return usersMapper.toDto(savedUser);

	}
	@Override
    public Optional<UserResponseDTO> getUserByIdDTO(Long id) {
        return userRepository.findById(id)
        		.map(usersMapper::toResponseDto);
    }
}
