package com.emd.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.emd.blog.model.Post;

public interface IPostService {

	List<Post> postsList();
	Page<Post> pageableList(Pageable pageable);
	Post savePost (Post post);
	Post editPost (Post post);
	Post showById (Integer postId);
	boolean deleteById (Integer postId);
	
	Page<Post> listAllPostsOrderByCreateAtDesc(Pageable pageable);
	

}
