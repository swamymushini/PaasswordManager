package com.example.pwdManager.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter
@Setter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String website;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	public Account(Long id, String website, String url, String username, String password) {
		super();
		this.id = id;
		this.website = website;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Account(String website, String url, String username, String password) {
		super();
		this.website = website;
		this.url = url;
		this.username = username;
		this.password = password;
	}

}
