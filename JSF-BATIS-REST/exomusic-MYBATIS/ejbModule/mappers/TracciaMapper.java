package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pojo.TracciaPOJO;

public interface TracciaMapper {

	final String findTracceByAlbum = "SELECT * FROM tracce WHERE id_album = #{id}";

	final String insert = "INSERT INTO album (nome,artista,anno_uscita,genere,copertina,data_inserimento) VALUES (#{nome},#{artista},#{annoUscita},#{genere},#{copertina},#{dataInserimento});";

	@Select(findTracceByAlbum)
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "titolo", column = "titolo"),
			@Result(property = "durata", column = "durata"),
			@Result(property = "idAlbum", column = "id_album")
			})
	public List<TracciaPOJO> findTracceByAlbum(Long id);

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(TracciaPOJO t);
}
