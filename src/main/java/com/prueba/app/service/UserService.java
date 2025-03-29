package com.prueba.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.prueba.app.DTO.UserDTO;
import com.prueba.app.DTO.UserMapper;
import com.prueba.app.entity.User;
import com.prueba.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<UserDTO> getAllUsersPage(Pageable pageable, Boolean active) {
		Page<UserDTO> usersPage;
		if (active != null) {
			usersPage = userRepository.findByActive(active, pageable).map(UserMapper.INSTANCE::userToUserDTO);
		} else
			usersPage = userRepository.findAll(pageable).map(UserMapper.INSTANCE::userToUserDTO);

		return usersPage;
	}

	public UserDTO getUser(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		return UserMapper.INSTANCE.userToUserDTO(userOptional.get());
	}

	public void saveUser(UserDTO newUserDTO) {
		User newUser = UserMapper.INSTANCE.userDTOtoUser(newUserDTO);
		newUser.setLastModified(LocalDateTime.now());
		this.userRepository.save(newUser);
	}

	public Boolean editUser(UserDTO modifiedUserDTO) {

		User modifiedUser = UserMapper.INSTANCE.userDTOtoUser(modifiedUserDTO);
		User actualUser = userRepository.findById(modifiedUser.getId()).get();

		actualUser.setId(modifiedUser.getId());
		actualUser.setActive(modifiedUser.getActive());
		actualUser.setApellido1(modifiedUser.getApellido1());
		actualUser.setApellido2(modifiedUser.getApellido2());
		actualUser.setEmail(modifiedUser.getEmail());
		actualUser.setLastModified(LocalDateTime.now());
		actualUser.setNombre(modifiedUser.getNombre());
		actualUser.setTelefono(modifiedUser.getTelefono());

		userRepository.save(actualUser);

		return true;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	/*
	 * Revisa que el email ingresado es valido segun 2 criterios: El email es valido
	 * si No existe para ningun usuario o Existe para un usuario desactivado
	 */
	public void availableEmailForUser(UserDTO user, BindingResult result) {
		List<User> emailHolders = userRepository.findAllByEmail(user.getEmail());
		for (User u : emailHolders) {
			if (!u.getId().equals(user.getId()) && u.getActive()) {
				result.rejectValue("email", "error.user",
						"El email ya está registrado por un usuario activo, ingrese uno diferente o desactive el usuario asociado.");
				return;
			}
		}
	}

	/*
	 * Registra el acceso manual
	 */
	public void checkUser(Long id) {
		User user = userRepository.findById(id).get();
		user.setLastAccess(LocalDateTime.now());

		userRepository.save(user);
	}

}
