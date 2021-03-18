package controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.Utente;
import services.MessaggiService;

@Controller
public class IndexController {
	
	private MessaggiService messaggiService;
	
	@Autowired
	public void setMessaggiService(MessaggiService messaggiService) {
		this.messaggiService = messaggiService;
	}

	@RequestMapping(value="/index")
	@Transactional
	public String index (@ModelAttribute Utente utente,Model model) {
		model.addAttribute("utente",utente);
		return "index";
	}
	@RequestMapping(value="/indietroUtente", method={RequestMethod.POST,RequestMethod.GET}) 
	public String indietroUtente (@ModelAttribute Utente utente,HttpSession session,Model model) {
		model.addAttribute("nuoviMessaggi",messaggiService.getNumeroMessaggiNonLetti((Utente)session.getAttribute("utenteAttivo")));
		model.addAttribute("utente",utente);
		return index(utente, model);
	}
	
}
