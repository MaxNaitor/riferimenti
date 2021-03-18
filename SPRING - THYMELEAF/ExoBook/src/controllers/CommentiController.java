package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Commento;
import model.Utente;
import services.CommentiService;

@Controller
@RequestMapping(value = "/commenti")
public class CommentiController {

	private CommentiService commentiService;
	private IndexController indexController;

	@Autowired
	public void setCommentiService(CommentiService commentiService) {
		this.commentiService = commentiService;
	}

	@Autowired
	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

	@RequestMapping(value = "/aggiungi", method = { RequestMethod.GET, RequestMethod.POST })
	public String aggiungi(@ModelAttribute("commento") Commento commento, Model model, HttpSession session) {
		commentiService.aggiungi(commento);
		model.addAttribute("avviso", "Commento pubblicato");
		return indexController.indietroUtente((Utente)session.getAttribute("utenteAttivo"), session, model);
	}
	
	@RequestMapping(value = "/elimina", method = { RequestMethod.GET, RequestMethod.POST })
	public String elimina(@ModelAttribute("commento") Commento commento, Model model, HttpSession session) {
		commentiService.elimina(commento);
		model.addAttribute("avviso", "Commento eliminato");
		return indexController.indietroUtente((Utente)session.getAttribute("utenteAttivo"), session, model);
	}

}
