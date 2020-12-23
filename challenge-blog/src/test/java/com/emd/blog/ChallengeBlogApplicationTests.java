package com.emd.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emd.blog.model.Post;
import com.emd.blog.service.IPostService;


//import com.emd.blog.model.Post;
//import com.emd.blog.service.IPostService;


@SpringBootTest
class ChallengeBlogApplicationTests {
	
	
	  @Autowired private IPostService service;
	 

	
	 @Test void testInicial() {
	
	 String mensaje = "JUnit está funcionando OK";
	 System.out.println(mensaje);
	 assertEquals("JUnit está funcionando OK", mensaje); }
	 
	 @Test void testSavePost() {
		 
		
		 Post post = new Post();
		 post.setPostId(10);
		 post.setTitle("JunitTest");
		 post.setContent("Estamos realizandoun test con Junit");
		 service.savePost(post);
		 
		 assertEquals("post", post);
		
		 
		 
	 }
	 
	
	

}
