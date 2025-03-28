package com.prueba.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
public @Data class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "El nombre debe contener al menos 1 caracter.")
	private String nombre;

	@NotEmpty(message = "El Apellido debe contener al menos 1 caracter.")
	private String apellido1;

	@NotNull
	private String apellido2;

	@NotEmpty(message = "El Telefono debe contener al menos 1 numero.")
	private String telefono;

	@NotEmpty(message = "El E-Mail no puede estar vacio, y debe contener un formato valido")
	@Email(message = "El E-Mail debe respetar un formato valido")
	private String email;

	private Boolean active = true;
	private LocalDateTime lastAccess;
	private LocalDateTime lastModified;

}
