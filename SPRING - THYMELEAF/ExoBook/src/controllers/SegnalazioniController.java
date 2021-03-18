package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Messaggio;
import model.Segnalazione;
import model.Utente;
import services.MessaggiService;
import services.PostService;
import services.SegnalazioniService;
import services.UtenteService;

@Controller
@RequestMapping(value = "/segnalazioni")
public class SegnalazioniController {

	private SegnalazioniService segnalazioniService;
	private MessaggiService messaggiService;
	private PostService postService;
	private UtenteService utenteService;

	@Autowired
	public void setSegnalazioniService(SegnalazioniService segnalazioniService) {
		this.segnalazioniService = segnalazioniService;
	}

	@Autowired
	public void setMessaggiService(MessaggiService messaggiService) {
		this.messaggiService = messaggiService;
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@Autowired
	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@RequestMapping(value = "/segnala", method = { RequestMethod.POST, RequestMethod.GET })
	public String segnala(@ModelAttribute("segnalazione") Segnalazione segnalazione, @ModelAttribute Utente utente,
			HttpSession session, Model model) {
		segnalazioniService.inviaSegnalazione(segnalazione);
		model.addAttribute("avviso", "Post di " + segnalazione.getUtenteSegnalato().getUsername() + " segnalato");
		model.addAttribute("nuoviMessaggi",
				messaggiService.getNumeroMessaggiNonLetti((Utente) session.getAttribute("utenteAttivo")));
		model.addAttribute("utente", utente);
		return "index";
	}

	@RequestMapping(value = "/risolvi", method = { RequestMethod.POST, RequestMethod.GET })
	public String risolvi(@ModelAttribute("segnalazione") Segnalazione segnalazione,
			@ModelAttribute Messaggio messaggio, @ModelAttribute Utente mittente, @RequestParam("op") String op,
			Model model) {
		segnalazione = segnalazioniService.getSegnalazione(segnalazione);
		// se la segnalazione è accettata,il post viene eliminato e invio un messaggio all'utente proprietario del post
		messaggio = new Messaggio();
		if (op.equalsIgnoreCase("accetta")) {
			mittente = utenteService.getUtente(999);
			messaggio.setMittente(mittente);
			messaggio.setDestinatario(segnalazione.getUtenteSegnalato());
			String testo = "Gentile " + segnalazione.getUtenteSegnalato().getUsername()
					+ ",la informo che il suo Post n " + segnalazione.getPostSegnalato().getId()
					+ " e' stato rimosso a seguito di una segnalazione";
			messaggio.setMessaggio(testo);
			segnalazioniService.eliminaSegnalazione(segnalazione);					
			postService.eliminaPost(segnalazione.getPostSegnalato());
			messaggiService.inviaMessaggio(messaggio);	
		} else {
			mittente = utenteService.getUtente(999);
			messaggio.setMittente(mittente);
			messaggio.setDestinatario(segnalazione.getUtenteSegnalatore());
			String testo = "Gentile " + segnalazione.getUtenteSegnalatore().getUsername()
					+ ",la informo che il Post n " + segnalazione.getPostSegnalato().getId() + " non verra' rimosso";
			messaggio.setMessaggio(testo);
			segnalazioniService.eliminaSegnalazione(segnalazione);	
			messaggiService.inviaMessaggio(messaggio);
		}
		model.addAttribute("segnalazione", segnalazione);
		model.addAttribute("segnalazioni", segnalazioniService.leggiSegnalazioni());
		return "visualizzaSegnalazioni";
	}

}
