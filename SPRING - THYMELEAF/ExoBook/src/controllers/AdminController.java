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

import exception.AdminException;
import exception.CredenzialiException;
import model.Amministratore;
import model.Segnalazione;
import model.Utente;
import services.AdminService;
import services.SegnalazioniService;
import services.UtenteService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	private AdminService adminService;
	private UtenteService utenteService;
	private SegnalazioniService segnalazioniService;

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@Autowired
	public void setUtentiService(UtenteService utentiService) {
		this.utenteService = utentiService;
	}

	@Autowired
	public void setSegnalazioniService(SegnalazioniService segnalazioniService) {
		this.segnalazioniService = segnalazioniService;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute Amministratore admin, Model model) {
		model.addAttribute("amministratore", admin);
		return "formAdmin";
	}

	@RequestMapping(value = "/accesso", method = { RequestMethod.GET, RequestMethod.POST })
	public String accesso(@Valid @ModelAttribute("amministratore") Amministratore admin, Errors errors, Model model,
			HttpSession session) {
		if (errors.hasErrors()) {
			return "formAdmin";
		}
		try {
			admin = adminService.accesso(admin);
			session.setAttribute("adminAttivo", admin);
			model.addAttribute("nuoveSegnalazioni", segnalazioniService.leggiSegnalazioni().size());
		} catch (CredenzialiException e) {
			model.addAttribute("messaggio", "Errore: Credenziali Errate!");
			return "forward:login";
		}
		return "admin";
	}

	@RequestMapping(value = "/visualizzaSegnalazioni", method = { RequestMethod.POST, RequestMethod.GET })
	public String visualizzaSegnalazioni(@ModelAttribute Segnalazione segnalazione, Model model) {
		model.addAttribute("segnalazione", segnalazione);
		model.addAttribute("segnalazioni", segnalazioniService.leggiSegnalazioni());
		return "visualizzaSegnalazioni";
	}

	@RequestMapping(value = "/nominaAdmin", method = { RequestMethod.POST, RequestMethod.GET })
	public String nominaAdmin(@ModelAttribute("amministratore") Amministratore admin, @RequestParam("id") String id,
			Model model) {
		Amministratore nuovoAdmin = new Amministratore();
		nuovoAdmin.setUsername(admin.getUsername());
		nuovoAdmin.setPassword(admin.getPassword());
		nuovoAdmin.setCodiceSicurezza(admin.getCodiceSicurezza());
		try {
			adminService.nominaAdmin(nuovoAdmin);
			model.addAttribute("idMod", Integer.parseInt(id));
			model.addAttribute("esito", "L'utente è ora un Moderatore");
		} catch (AdminException e) {
			model.addAttribute("idMod", Integer.parseInt(id));
			model.addAttribute("esito", "L'utente è già un Moderatore");
		}
		model.addAttribute("nuoveSegnalazioni", segnalazioniService.leggiSegnalazioni().size());
		return "forward:visualizzaIscritti";
	}

	@RequestMapping(value = "/visualizzaIscritti", method = { RequestMethod.POST, RequestMethod.GET })
	public String visualizzaIscritti(@ModelAttribute ArrayList<Utente> utenti, @ModelAttribute Amministratore admin,
			@ModelAttribute Utente utente, Model model) {
		utenti = utenteService.getListaUtenti();
		model.addAttribute("utenti", utenti);
		model.addAttribute("amministratore", admin);
		model.addAttribute("nuoveSegnalazioni", segnalazioniService.leggiSegnalazioni().size());
		return "admin";
	}

	@RequestMapping(value = "/indietro", method = { RequestMethod.POST, RequestMethod.GET })
	public String indietro(@ModelAttribute Utente utente, HttpSession session, Model model) {
		model.addAttribute("nuoveSegnalazioni", segnalazioniService.leggiSegnalazioni().size());
		return "admin";
	}

}
