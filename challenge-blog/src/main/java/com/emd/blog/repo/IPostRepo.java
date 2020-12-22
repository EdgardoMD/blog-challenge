package com.emd.blog.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emd.blog.model.Post;

public interface IPostRepo extends JpaRepository<Post, Integer> {
	
	@Query(value = "select * from posts order by create_at DESC", nativeQuery = true)
	Page<Post> listAllPostsOrderByCreateAtDesc(Pageable pageable);

}
