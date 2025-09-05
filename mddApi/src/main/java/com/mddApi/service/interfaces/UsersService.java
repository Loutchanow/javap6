package com.mddApi.service.interfaces;
import java.util.Optional;
import com.mddApi.dto.UserResponseDTO;
import com.mddApi.dto.UsersDTO;



public interface UsersService {

	
	 UsersDTO save(UsersDTO usersDTO);
	
     Optional<UserResponseDTO> getUserByIdDTO(Long id);
}
