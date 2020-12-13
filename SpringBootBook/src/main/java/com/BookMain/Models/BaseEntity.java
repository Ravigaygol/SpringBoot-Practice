package com.BookMain.Models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass

public abstract class BaseEntity  {
	private static final long serialVersionUID = -3952398590854367799L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return this.id;
		
	}
	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseEntity(Long id) {
		super();
		this.id = id;
	}
	
}