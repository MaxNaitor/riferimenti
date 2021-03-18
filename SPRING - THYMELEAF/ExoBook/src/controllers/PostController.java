package controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Commento;
import model.Post;
import model.Segnalazione;
import model.Utente;
import services.CommentiService;
import services.PostService;
import services.UtenteService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	private PostService postService;
	private UtenteService utenteService;
	private CommentiService commentiService;
	private IndexController indexController;

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@Autowired
	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@Autowired
	public void setCommentiService(CommentiService commentiService) {
		this.commentiService = commentiService;
	}

	@Autowired
	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

	@RequestMapping(value = "/form", method = { RequestMethod.POST, RequestMethod.GET })
	public String creaPost(@ModelAttribute Post post, Model model) {
		model.addAttribute("post", post);
		return "postForm";
	}

	@RequestMapping(value = "/pubblica", method = { RequestMethod.POST, RequestMethod.GET })
	public String pubblicaPost(@Valid @ModelAttribute("post") Post post, Errors errors, @ModelAttribute Utente utente,
			HttpSession session, Model model) {
		if (errors.hasErrors()) {
			return "postForm";
		}
		postService.pubblicaPost(post);
		model.addAttribute("avviso", "Post pubblicato");
		return indexController.indietroUtente(utente, session, model);
	}

	@RequestMapping(value = "/visualizzaBacheca", method = { RequestMethod.POST, RequestMethod.GET })
	public String visualizzaBacheca(@ModelAttribute("utente") Utente utente, @ModelAttribute Post post,
			@ModelAttribute Segnalazione segnalazione, Model model) {
		utente = utenteService.getUtente(utente.getId());
		setAttributeBacheca(model, utente, post, segnalazione);
		return "bacheca";
	}

	@RequestMapping(value = "/aggiungiLike", method = { RequestMethod.POST, RequestMethod.GET })
	public String aggiungiLike(@ModelAttribute("post") Post post, @ModelAttribute Utente utente,
			@ModelAttribute Segnalazione segnalazione, Model model) {
		utente = utenteService.getUtente(post.getUtente().getId());
		postService.aggiungiLike(post);
		setAttributeBacheca(model, utente, post, segnalazione);
		return "bacheca";
	}

	@RequestMapping(value = "/elimina", method = { RequestMethod.POST, RequestMethod.GET })
	public String elimina(@ModelAttribute("post") Post post, @ModelAttribute Utente utente,
			@ModelAttribute Segnalazione segnalazione, Model model) {
		utente = utenteService.getUtente(post.getUtente().getId());
		postService.eliminaPost(post);
		setAttributeBacheca(model, utente, post, segnalazione);
		return "bacheca";
	}

	private void setAttributeBacheca(Model model, Utente utente, Post post, Segnalazione segnalazione) {
		ArrayList<Post> lista = postService.getPostUtente(utente);
		ArrayList<Commento> commenti = commentiService.recuperaCommenti();
		model.addAttribute("utente", utente);
		model.addAttribute("post", post);
		model.addAttribute("listaPost", lista);
		model.addAttribute("segnalazione", segnalazione);
		model.addAttribute("commenti", commenti);
		model.addAttribute("commento", new Commento());
	}

}
