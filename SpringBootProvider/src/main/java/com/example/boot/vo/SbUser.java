package com.example.boot.vo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sbuser")
public class SbUser {
	
	@Id
	@Column(name="id", unique = true, nullable = true)
	@GenericGenerator(name = "idGenerator", strategy = "native")
	private Integer id;
	
	@Column(name="name")
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "SbUser [id=" + id + ", name=" + name + "]";
	}

}
