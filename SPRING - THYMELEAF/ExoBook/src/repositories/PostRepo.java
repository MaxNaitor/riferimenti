package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.Post;

public interface PostRepo extends CrudRepository<Post, Long> {

	@Query(value="SELECT * FROM post p WHERE p.id_utente = :id",nativeQuery=true)
	List<Post> findByUtente(@Param("id") int id);
}
