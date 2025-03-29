package com.prueba.app.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public @Data class UserDTO {
	
	private Long id;
	
	@NotEmpty(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s-]+$", message = "El nombre solo puede contener letras y espacios")
	private String nombre;
	
	@NotEmpty(message = "El Apellido es obligatorio")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s-]+$", message = "El Apellido solo puede contener letras y espacios")
	private String apellido1;
	
	@NotNull
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s-]+$", message = "El Apellido solo puede contener letras y espacios")
	private String apellido2;
	
    @NotEmpty(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos numericos.")
	private String telefono;
    
    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
	private String email;
    
    @NotNull(message = "El estado es obligatorio")
	private Boolean active;
    
	private String lastAccess;
	private String lastModified;
	
}
