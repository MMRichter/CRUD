package com.prueba.app.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.prueba.app.entity.User;

@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
    @Mapping(source = "lastAccess", target = "lastAccess", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(source = "lastModified", target = "lastModified", dateFormat = "dd-MM-yyyy HH:mm:ss")
	UserDTO userToUserDTO(User user);
	
    @Mapping(source = "lastAccess", target = "lastAccess", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(source = "lastModified", target = "lastModified", dateFormat = "dd-MM-yyyy HH:mm:ss")
    User userDTOtoUser(UserDTO userDTO);
	
	
}
