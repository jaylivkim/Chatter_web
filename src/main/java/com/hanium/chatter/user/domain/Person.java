package com.hanium.chatter.user.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_person")
@Getter @Setter
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id", unique=true, length=64)
	private String userId;

	@Column(name="password", length=256)
	private String password;
	
	@Column(name="user_name", length=128)
	private String userName;
	
	@OneToMany(mappedBy="person", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private Set<PersonRole> personRoles;
}
