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

import model.Messaggio;
import model.Utente;
import services.MessaggiService;
import services.UtenteService;

@Controller
@RequestMapping(value="/messaggi")
public class MessaggiController {

	private MessaggiService messaggiService;
	private UtenteService utenteService;
	private IndexController indexController;

	@Autowired
	public void setMessaggiService(MessaggiService messaggiService) {
		this.messaggiService = messaggiService;
	}

	@Autowired
	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}
	
	@Autowired
	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

	@RequestMapping(value = "/messaggiForm", method = {RequestMethod.POST,RequestMethod.GET})
	public String messaggioForm(@ModelAttribute("destinatario") Utente destinatario, @ModelAttribute Messaggio msg,
			@RequestParam("idMsg") String id, Model model) {
		int idMsg = Integer.parseInt(id);
		// se entro qui rispondendo a un messaggio,imposto automaticamente quel
		// messaggio come letto
		if (idMsg != 0) {
			msg = messaggiService.getMessaggio(idMsg);
			messaggiService.segnaComeLetto(msg);
		}
		model.addAttribute("messaggio", new Messaggio());
		return "messaggiForm";
	}

	@RequestMapping(value = "/invia", method = {RequestMethod.POST,RequestMethod.GET})
	public String invia(@Valid @ModelAttribute("messaggio") Messaggio msg,Errors errors, Model model) {
		if (errors.hasErrors()) {
			Utente destinatario = utenteService.getUtente(msg.getDestinatario().getId());
			model.addAttribute("destinatario",destinatario);
			return "messaggiForm";
		}
		messaggiService.inviaMessaggio(msg);
		model.addAttribute("avviso", "Messaggio inviato");
		return "forward:indietro";
	}

	@RequestMapping(value = "/messaggiRicevuti", method = {RequestMethod.POST,RequestMethod.GET})
	public String getMessaggiRicevuti(HttpSession session, @ModelAttribute Utente destinatario, Model model,
			@ModelAttribute Messaggio messaggio) {
		ArrayList<Messaggio> messaggi = messaggiService.getMessaggi((Utente) session.getAttribute("utenteAttivo"));
		model.addAttribute("messaggi", messaggi);
		model.addAttribute("destinatario", destinatario);
		model.addAttribute("messaggio", messaggio);
		return "visualizzaMessaggiRicevuti";
	}

	@RequestMapping(value = "/segnaLetto", method = {RequestMethod.POST,RequestMethod.GET})
	public String segnaLetto(@ModelAttribute("messaggio") Messaggio msg) {
		msg = messaggiService.getMessaggio(msg.getId());
		messaggiService.segnaComeLetto(msg);
		return "forward:messaggiRicevuti";
	}

	@RequestMapping(value = "/cancella", method = {RequestMethod.POST,RequestMethod.GET})
	public String cancellaMessaggio(@ModelAttribute("messaggio") Messaggio msg) {
		msg = messaggiService.getMessaggio(msg.getId());
		messaggiService.cancellaMessaggio(msg);
		return "forward:messaggiRicevuti";
	}
	
	@RequestMapping(value="/indietro", method={RequestMethod.POST,RequestMethod.GET}) 
	public String indietroUtente (@ModelAttribute Utente utente,HttpSession session,Model model) {
		return indexController.indietroUtente(utente, session, model);
	}
}
