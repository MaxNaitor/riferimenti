package repositories;

import org.springframework.data.repository.CrudRepository;

import model.Amministratore;

public interface AdminRepo extends CrudRepository<Amministratore, Long> {

	Amministratore findByUsernameAndPasswordAndCodiceSicurezzaAndRango(String username,String password,String codiceSicurezza,String rango);
	
}
