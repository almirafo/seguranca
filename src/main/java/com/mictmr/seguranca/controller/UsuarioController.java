package com.mictmr.seguranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mictmr.seguranca.enums.Status;
import com.mictmr.seguranca.model.Nivel;
import com.mictmr.seguranca.model.Usuario;
import com.mictmr.seguranca.reposiroty.NivelRepo;
import com.mictmr.seguranca.reposiroty.UsuarioRepo;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepo usuarioRepo;

	@Autowired
	NivelRepo nivelRepo;

	
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		return new ModelAndView("CadastroUsuario");
	}
	

	
	@RequestMapping
	public ModelAndView pesquisaUsuarios(){
		
		List<Usuario> usuarios=   usuarioRepo.findAll();
		return new ModelAndView("PesquisaUsuarios").addObject("usuarios", usuarios);
	}

	
	
	@RequestMapping(value="/salvar",  method=RequestMethod.POST)
	public ModelAndView salvar(Usuario usuario){
		
		System.out.println(usuario.getNome());
		//Após salvar volta a lista de usuários
		usuarioRepo.save(usuario);
		
		ModelAndView mv= new ModelAndView("CadastroUsuario");
		mv.addObject("mensagem", "Usuário salvo com sucesso!");
		return mv;
	}


	

	@RequestMapping(value="/editar/{idUsuario}", method=RequestMethod.POST)
	public String edit(@PathVariable("idUsuario") long id){
		return "EditaUsuarios";
	
	}


	@RequestMapping(value="/apagar/{idUsuario}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("idUsuario") long id){
		return "ListaUsuarios";
	
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
