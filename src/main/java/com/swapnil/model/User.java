package com.swapnil.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Size(min = 2 ,message = "name character should be greater than 2")
	private String name;
	@Email(message = "Email is mandatory")
	private String email;
	@Size(min = 10,max = 10 ,message = "mobile number should be 10 digit")
	private String mobile;
	private String password;
	
	
}
