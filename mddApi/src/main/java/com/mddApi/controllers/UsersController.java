package com.mddApi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.mddApi.dto.UserResponseDTO;
import com.mddApi.dto.UsersDTO;
import com.mddApi.model.Users;
import com.mddApi.repository.UsersRepository;
import com.mddApi.service.interfaces.UsersService;
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
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UsersDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UsersDTO usersDTO,
            Principal principal) {

        String currentEmail = principal.getName();
        Users currentUser = usersRepository.findByEmail(currentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!currentUser.getId().equals(id)) {
            return ResponseEntity.status(403).build();
        }


        currentUser.setName(usersDTO.getName());
        if (usersDTO.getEmail() != null && !usersDTO.getEmail().isBlank()) {
            currentUser.setEmail(usersDTO.getEmail());
        }

        if (usersDTO.getPassword() != null && !usersDTO.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(usersDTO.getPassword());
            currentUser.setPassword(encodedPassword);
        }

        Users updated = usersRepository.save(currentUser);
        UsersDTO dto = usersMapper.toDto(updated);

        return ResponseEntity.ok(dto);
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
