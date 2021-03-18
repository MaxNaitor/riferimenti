package repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.Utente;

public interface UtenteRepo extends CrudRepository<Utente, Long> {

	Utente findByUsernameAndPassword (String username,String password);
	
	List<Utente> findByIdNot (int id);
	
	Utente findById(int id);
}
