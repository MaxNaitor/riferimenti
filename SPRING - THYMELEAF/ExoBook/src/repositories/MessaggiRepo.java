package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.Messaggio;

public interface MessaggiRepo extends CrudRepository<Messaggio, Long> {
	
	@Query(value="SELECT * FROM messaggi m WHERE m.id_destinatario = :id",nativeQuery = true)
	List<Messaggio> findByIdDestinatario(@Param("id") int id);
	
	Messaggio findById(int id);
}
