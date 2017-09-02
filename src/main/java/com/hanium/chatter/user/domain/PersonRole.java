package com.hanium.chatter.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_person_role")
@Getter @Setter
public class PersonRole {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name="role_name", nullable=false, unique=true, length=128)
	private String roleName;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Person person;

}
