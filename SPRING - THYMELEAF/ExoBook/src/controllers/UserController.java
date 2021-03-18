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
import org.springframework.web.bind.annotation.RequestParam;

import exception.CredenzialiException;
import model.Utente;
import services.MessaggiService;
import services.UtenteService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private UtenteService utenteService;
	private MessaggiService messaggiService;
	private IndexController indexController;

	@Autowired
	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@Autowired
	public void setMessaggiService(MessaggiService messaggiService) {
		this.messaggiService = messaggiService;
	}

	@Autowired
	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(@ModelAttribute Utente utente, @RequestParam("op") String op, Model model) {
		model.addAttribute("op", op);
		return "form";
	}

	@RequestMapping(value = "/Registrati", method = { RequestMethod.POST, RequestMethod.GET })
	public String registrazione(@Valid @ModelAttribute("utente") Utente utente, Errors errors, HttpSession session,
			Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("op", "Registrati");
			return "form";
		}
		try {
			utente = utenteService.registrazione(utente);
			session.setAttribute("utenteAttivo", utente);
			model.addAttribute("nuoviMessaggi", messaggiService.getNumeroMessaggiNonLetti(utente));
			return indexController.index(new Utente(), model);
		} catch (CredenzialiException e) {
			model.addAttribute("messaggio", "Errore: Username non disponibile!");
			return "forward:login?op=Registrati";
		}
	}

	@RequestMapping(value = "/Accedi", method = { RequestMethod.POST, RequestMethod.GET })
	public String accesso(@Valid @ModelAttribute("utente") Utente utente, Errors errors, Model model,
			HttpSession session) {
		if (errors.hasErrors()) {
			model.addAttribute("op", "Accedi");
			return "form";
		}
		try {
			utente = utenteService.accesso(utente);
			session.setAttribute("utenteAttivo", utente);
			model.addAttribute("nuoviMessaggi", messaggiService.getNumeroMessaggiNonLetti(utente));
			return indexController.index(new Utente(), model);
		} catch (CredenzialiException e) {
			model.addAttribute("messaggio", "Errore: Credenziali errate!");
			return "forward:login?op=Accedi";
		}
	}

	@RequestMapping(value = "/visualizzaIscritti", method = { RequestMethod.POST, RequestMethod.GET })
	public String visualizzaIscritti(HttpSession session, @ModelAttribute ArrayList<Utente> utenti,
			@ModelAttribute Utente destinatario, Model model) {
		utenti = utenteService.getListaUtenti((Utente) session.getAttribute("utenteAttivo"));
		model.addAttribute("nuoviMessaggi",
				messaggiService.getNumeroMessaggiNonLetti((Utente) session.getAttribute("utenteAttivo")));
		model.addAttribute("utenti", utenti);
		model.addAttribute("destinatario", destinatario);
		return "index";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("messaggio", "Logout effettuato");
		return "index";
	}
	
}
