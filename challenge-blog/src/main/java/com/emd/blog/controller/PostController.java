package com.emd.blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emd.blog.model.Category;
import com.emd.blog.model.Post;
import com.emd.blog.repo.ICategoryRepo;
import com.emd.blog.service.IPostService;
import com.emd.blog.service.IUploadFileService;
import com.emd.blog.util.PageRender;

@Controller
public class PostController {
	
	@Autowired
	private IPostService service;
	@Autowired
	private ICategoryRepo repo;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping({"/", "/list"})
	public String inicio(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Post> posts = service.listAllPostsOrderByCreateAtDesc(pageRequest);
		
		PageRender<Post> pageRender = new PageRender<Post>("/list", posts);
		model.addAttribute("posts", posts);
		model.addAttribute("page", pageRender);
		return "index";
	}
	

	
	@GetMapping ("/posts/new")
	public String formNewPost (Map<String, Object> model) {
		Post post = new Post();
		List<Category> categories = repo.findAll();
		model.put("post", post);
		model.put("categories", categories);
		return "post-form";
	}
	
	@GetMapping ("/posts/edit/{postId}")
	public ModelAndView editPost(@PathVariable(value = "postId") Integer postId) {
		ModelAndView mav = new ModelAndView("post-form-edit");
		Post post = service.showById(postId);
		List<Category> categories = repo.findAll();
		mav.addObject("post", post);
		mav.addObject("categories", categories);
		return mav;
	}
	
	@PostMapping("/posts/save")
	public String savePost(@Validated Post post, BindingResult result, @RequestParam("file") MultipartFile image, Model model, RedirectAttributes flash) throws IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario Nuevo Post");
			return "post-form";
		}
		
		if (!image.isEmpty()) {

			if (post.getPostId() != null && post.getPostId() > 0 && post.getImage() != null
					&& post.getImage().length() > 0) {

				uploadFileService.delete(post.getImage());
			}
			
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			post.setImage(uniqueFilename);
		}
		String mensajeFlash = (post.getPostId() != null) ? "POST editado con éxito!" : "POST creado con éxito!";

		service.savePost(post);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/";
	}
	
	@GetMapping("/posts/show")
	public String verPorId (@RequestParam Integer postId, Model model) {
		
		Post post = service.showById(postId);
		List<Category> categories = repo.findAll();
		if(post.getPostId() == null) {
			return "redirect:/";
		}
		model.addAttribute("post", post);
		model.addAttribute("categories", categories);
		return "post-view";
	}
	
	@GetMapping("posts/delete/{postId}")
	public String eliminarPost(@PathVariable (value = "postId") Integer postId) {
		//Posts post = service.showById(postId);
		service.deleteById(postId);
		return "redirect:/";
	}
	

}
