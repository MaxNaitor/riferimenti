package mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pojo.AlbumPOJO;
import pojo.UtentePOJO;

public interface UtenteMapper {

	final String insert = "INSERT INTO utenti (nome,cognome,data_nascita,sesso,email,pass) VALUES (#{nome},#{cognome},#{dataNascita},#{sesso},#{email},#{pass});";
	final String accesso = "SELECT * FROM utenti WHERE email = #{email} AND pass = #{pass};";
	final String acquista = "INSERT INTO acquisti (id_utente,id_album,data_acquisto) VALUES (#{idUtente},#{idAlbum},NOW());";
	final String restituisci = "DELETE FROM acquisti WHERE id_utente = #{idUtente} AND id_album = #{idAlbum};";
	

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(UtentePOJO u);

	@Select(accesso)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "cognome",column = "cognome"),
			@Result(property = "dataNascita",column = "data_nascita"),
			@Result(property = "sesso",column = "sesso"),
			@Result(property = "email",column = "email"),
			@Result(property = "pass",column = "pass")
	})
	public UtentePOJO accesso(UtentePOJO u);

	@Insert(acquista)
	public void acquista(@Param("idUtente") Long idUtente, @Param("idAlbum") Long idAlbum);

	@Delete(restituisci)
	public void restituisci(@Param("idUtente") Long idUtente, @Param("idAlbum") Long idAlbum);

	public List<AlbumPOJO> getAcquisti(UtentePOJO u);

	public List<UtentePOJO> getAll();

}
