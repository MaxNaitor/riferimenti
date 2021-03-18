package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Segnalazione;
import repositories.SegnalazioniRepo;

@Service
public class SegnalazioniService {

	private SegnalazioniRepo segnalazioniRepo;

	@Autowired
	public void setSegnalazioniRepo(SegnalazioniRepo segnalazioniRepo) {
		this.segnalazioniRepo = segnalazioniRepo;
	}

	public void inviaSegnalazione(Segnalazione s) {
		segnalazioniRepo.save(s);
	}

	public ArrayList<Segnalazione> leggiSegnalazioni() {
		return (ArrayList<Segnalazione>) segnalazioniRepo.findAll();
	}

	public void eliminaSegnalazione(Segnalazione s) {
		for (Segnalazione seg : segnalazioniRepo.findByPost(s.getPostSegnalato().getId())) {
			segnalazioniRepo.delete(seg);
		}
	}

	public Segnalazione getSegnalazione(Segnalazione s) {
		return segnalazioniRepo.findById(s.getId());
	}

}
