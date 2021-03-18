package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.Segnalazione;

public interface SegnalazioniRepo extends CrudRepository<Segnalazione, Long> {
	
	Segnalazione findById (int id);
	
	@Query(value="SELECT * FROM segnalazioni s WHERE s.id_post_segnalato = :id",nativeQuery=true)
	List<Segnalazione> findByPost(@Param("id")int id);
}
