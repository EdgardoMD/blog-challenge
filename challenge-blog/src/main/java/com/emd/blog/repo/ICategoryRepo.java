package com.emd.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emd.blog.model.Category;

public interface ICategoryRepo extends JpaRepository<Category, Integer> {

}
