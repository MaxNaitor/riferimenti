package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Messaggio;
import model.Utente;
import repositories.MessaggiRepo;

@Service
public class MessaggiService {

	private MessaggiRepo messaggiRepo;

	@Autowired
	public void setMessaggiRepo(MessaggiRepo messaggiRepo) {
		this.messaggiRepo = messaggiRepo;
	}

	public ArrayList<Messaggio> getMessaggi(Utente utente) {
		return (ArrayList<Messaggio>) messaggiRepo.findByIdDestinatario(utente.getId());
	}

	public void inviaMessaggio(Messaggio messaggio) {
		messaggiRepo.save(messaggio);
	}

	public void segnaComeLetto(Messaggio messaggio) {
		messaggio.setStato("Letto");
		messaggiRepo.save(messaggio);
	}

	public Messaggio getMessaggio(int id) {
		return messaggiRepo.findById(id);
	}

	public int getNumeroMessaggiNonLetti(Utente utente) {
		int nonLetti = 0;
		for (Messaggio m : messaggiRepo.findByIdDestinatario(utente.getId())) {
			if (m.getStato().equalsIgnoreCase("non letto")) {
				nonLetti++;
			}
		}
		return nonLetti;
	}

	public void cancellaMessaggio(Messaggio messaggio) {
		messaggiRepo.delete(messaggio);
	}
}
