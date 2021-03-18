package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import exception.CredenzialiException;
import model.Utente;
import repositories.UtenteRepo;

@Service
public class UtenteService {

	private UtenteRepo utenteRepo;

	@Autowired
	public void setUtenteRepo(UtenteRepo utenteRepo) {
		this.utenteRepo = utenteRepo;
	}

	public Utente registrazione(Utente utente) throws CredenzialiException {
		try {
			return utenteRepo.save(utente);
		} catch (DataIntegrityViolationException e) {
			throw new CredenzialiException();
		}		
	}

	public Utente accesso(Utente utente) throws CredenzialiException {
		Utente user = utenteRepo.findByUsernameAndPassword(utente.getUsername(), utente.getPassword());
		if (user == null) {
			throw new CredenzialiException();
		}
		return user;
	}

	public Utente getUtente(int id) {
		return utenteRepo.findById(id);
	}

	public ArrayList<Utente> getListaUtenti(Utente utente) {
		return (ArrayList<Utente>) utenteRepo.findByIdNot(utente.getId());
	}
	
	public ArrayList<Utente> getListaUtenti() {
		return (ArrayList<Utente>) utenteRepo.findAll();
	}
}
