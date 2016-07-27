package com.mictmr.seguranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mictmr.seguranca.enums.Status;
import com.mictmr.seguranca.model.Nivel;
import com.mictmr.seguranca.model.Usuario;
import com.mictmr.seguranca.reposiroty.NivelRepo;
import com.mictmr.seguranca.reposiroty.UsuarioRepo;
import com.mictmr.seguranca.service.SecurityService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final String CADASTROUSUARIOVIEW = "CadastroUsuario";

	@Autowired
	UsuarioRepo usuarioRepo;

	@Autowired
	NivelRepo nivelRepo;

	@Autowired
	SecurityService securityService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTROUSUARIOVIEW);
		mv.addObject(new Usuario());
		return mv;
	}
	

	
	@RequestMapping
	public ModelAndView pesquisaUsuarios(){
		
		List<Usuario> usuarios=   usuarioRepo.findAll();
		return new ModelAndView("PesquisaUsuarios").addObject("usuarios", usuarios);
	}

	
	
	@RequestMapping(value="/salvar",  method=RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors erros, RedirectAttributes redirectAttributes){
		
		if(erros.hasErrors()){
			return CADASTROUSUARIOVIEW;
		}
		
		System.out.println(usuario.getNome());
		usuarioRepo.save(usuario);
		redirectAttributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		
		return "redirect:/usuario/novo";
	}


	

	@RequestMapping(value="{idUsuario}", method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("idUsuario") Usuario usuario){
		ModelAndView mv = new ModelAndView(CADASTROUSUARIOVIEW); 
		mv.addObject(usuario);
		return mv;
	
	}


	@RequestMapping(value="{idUsuario}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("idUsuario") long id, RedirectAttributes attributes){
		
		usuarioRepo.delete(id);
		attributes.addFlashAttribute("mensagem", "Usuario excluído com sucesso!");
		return "redirect:/usuario";
	
	}

	
	@ModelAttribute("todosStatus")
	public List<Status> todosStatusTitulo() {
		return Arrays.asList(Status.values());
	}
	

	@ModelAttribute("todosNiveis")
	public List<Nivel> todosNiveis() {
		//return Arrays.asList(Nivel.values());
		
		return nivelRepo.findAll();
	}


	@ModelAttribute("login")
	@RequestMapping(value="/{email}/{senha}", method=RequestMethod.POST)
	public Usuario login(@PathVariable("email") String email, @PathVariable("senha") String senha) {
		//return Arrays.asList(Nivel.values());
		
		return usuarioRepo.findByEmailAndSenha(email, senha);
	}

	
	
}
