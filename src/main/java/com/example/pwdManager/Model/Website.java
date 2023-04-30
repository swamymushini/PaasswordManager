package com.example.pwdManager.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "websites")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Website {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String url;

	@Column(nullable = true)
	private String logo;

	@OneToMany(mappedBy = "website")
	@JsonBackReference
	private List<Account> accounts = new ArrayList<>();

	public Website(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

}
