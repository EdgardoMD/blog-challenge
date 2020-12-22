package com.emd.blog.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emd.blog.model.Post;
import com.emd.blog.repo.IPostRepo;
import com.emd.blog.service.IPostService;

@Service
public class PostServiceImpl implements IPostService{
	
	@Autowired
	private IPostRepo repo;

	@Override
	public List<Post> postsList() {
		
		return repo.findAll();
	}

	@Override
	public Post savePost(Post post) {
		
		return repo.save(post);
	}

	@Override
	public Post editPost(Post post) {
		
		return repo.save(post);
	}

	@Override
	public Post showById(Integer postId) {
		Optional<Post> op = repo.findById(postId);
		return op.isPresent() ? op.get() : new Post();
	}

	@Override
	public boolean deleteById(Integer postId) {
		repo.deleteById(postId);
		return true;
	}

	@Override
	public Page<Post> pageableList(Pageable pageable) {
		
		return repo.findAll(pageable);
	}

	@Override
	public Page<Post> listAllPostsOrderByCreateAtDesc(Pageable pageable) {
		
		return repo.listAllPostsOrderByCreateAtDesc(pageable);
	}
}
