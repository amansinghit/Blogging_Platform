package com.core.BloggingApplication.payloads;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private int id;
	@NotEmpty//   apply @Valid in controller+add dependency spring-boot-starter-validation
	@Size(min=4,message="User name must be 4 character")
	private String name;
	@Email(message="Email is not valid")//if this validation fails then this message displyed
	private String email;
	@NotEmpty
	@Size(min=5,max=10,message="Password must be of 5 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()-_=+{}|;:'\",.<>?]).*$")
	private String password;
	@NotEmpty(message="give atleast 4 char")
	private String about;

}
