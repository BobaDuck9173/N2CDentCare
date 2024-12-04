package com.example.N2CDentCare.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Username")
	private String Username;
	
	@Column(name = "Password")
	private String Password;
	
	@Column(name = "Id")
	private String Id;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "Account [Username=" + Username + ", Password=" + Password + ", Id=" + Id + "]";
	}

	public Account(String username, String password, String id) {
		super();
		Username = username;
		Password = password;
		Id = id;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
