package com.mictmr.seguranca.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mictmr.seguranca.model.Usuario;
import com.mictmr.seguranca.reposiroty.NivelRepo;
import com.mictmr.seguranca.service.SecurityService;

@Controller
public class MainController {
	@Autowired
	SecurityService securityService;
	
	@Autowired
	NivelRepo nivelRepo;
	
	@RequestMapping(value = { "/home**",}, method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("home");
		
		SecurityContext ctx= SecurityContextHolder.getContext();
		
		
		System.out.println(ctx.getAuthentication().getName());
		return model;

	}
	
	
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView model = new ModelAndView();
		model.addObject(new Usuario());
		model.setViewName("register");
		return model;
	}
	

	@RequestMapping(value="/register/salvar",  method=RequestMethod.POST)
	public ModelAndView register(Usuario usuario, Errors erros){
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		if(erros.hasErrors()){
			return model;
		}
		
		usuario.setNivel(nivelRepo.findOne(1l));
		System.out.println(usuario.getNome());
		securityService.save(usuario);
		return model;
	}

	
	
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}

}