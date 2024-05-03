package com.core.BloggingApplication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="Name" , nullable=false)
private String name;

	private String email;
	private String password;
	private String About;

}
