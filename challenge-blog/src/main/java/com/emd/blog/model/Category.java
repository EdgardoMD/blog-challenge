package com.emd.blog.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
	
	@Id
	private Integer categoryId;
	private String name;
}
