package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Commento;
import repositories.CommentiRepository;

@Service
public class CommentiService {

	private CommentiRepository commentiRepo;

	@Autowired
	public void setCommentiRepo(CommentiRepository commentiRepo) {
		this.commentiRepo = commentiRepo;
	}

	public void aggiungi(Commento commento) {
		commentiRepo.save(commento);
	}

	public void elimina(Commento commento) {
		commentiRepo.delete(commento);
	}

	public ArrayList<Commento> recuperaCommenti() {
		return (ArrayList<Commento>) commentiRepo.findAll();
	}

}
