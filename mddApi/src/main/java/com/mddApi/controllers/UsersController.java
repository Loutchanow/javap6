package com.mddApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.mddApi.dto.UserResponseDTO;
import com.mddApi.dto.UsersDTO;
import com.mddApi.model.Users;
import com.mddApi.repository.UsersRepository;
import com.mddApi.service.UsersService;
import com.mddApi.service.mapper.UsersMapper;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/user")
public class UsersController {
	
	@Autowired
	private UsersRepository usersRepository;

    private final UsersService usersService;
    
	@Autowired
	private UsersMapper usersMapper;
    
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        return usersService.getUserByIdDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    public UsersDTO me(Authentication authentication) {
        String email = authentication.getName(); 
        Users user = usersRepository.findByEmail(email).orElseThrow();
	    UsersDTO dto = usersMapper.toDto(user);
        
        return dto;
    }
}
