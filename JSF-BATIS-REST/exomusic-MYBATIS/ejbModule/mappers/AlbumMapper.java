package mappers;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import pojo.AlbumPOJO;

public interface AlbumMapper {
	
	final String insert = "INSERT INTO album (nome,artista,anno_uscita,genere,copertina,data_inserimento) VALUES (#{nome},#{artista},#{annoUscita},#{genere},#{copertina},#{dataInserimento});";
	
	final String findById = "SELECT * FROM album WHERE id = #{id}";
	
	final String findDataAcquisto = "SELECT data_acquisto FROM acquisti WHERE id_utente = #{idUtente} AND id_album = #{idAlbum};";
	
	final String getAlbumsByUtente = "SELECT * FROM album WHERE id IN (SELECT id_album as id FROM acquisti WHERE id_utente = #{id}) ORDER BY data_inserimento DESC;";
	
	final String getAlbumsByUtenteOrderNome = "SELECT * FROM album WHERE id IN (SELECT id_album as id FROM acquisti WHERE id_utente = #{id}) ORDER BY nome ASC;";
	
	final String getAlbumsByUtenteOrderGenere = "SELECT * FROM album WHERE id IN (SELECT id_album as id FROM acquisti WHERE id_utente = #{id}) ORDER BY genere ASC;";
	
	final String getAlbumsByUtenteOrderUscita = "SELECT * FROM album WHERE id IN (SELECT id_album as id FROM acquisti WHERE id_utente = #{id}) ORDER BY anno_uscita DESC;";
	
	final String getAlbumsByUtenteOrderArtista = "SELECT * FROM album WHERE id IN (SELECT id_album as id FROM acquisti WHERE id_utente = #{id}) ORDER BY artista ASC;";
	
	final String getAll = "SELECT * FROM album";
	
	final String getAllByArtista = "SELECT * FROM album ORDER BY artista ASC";
	
	final String getAllByUscita = "SELECT * FROM album ORDER BY anno_uscita DESC";
	
	final String getAllByNome = "SELECT * FROM album ORDER BY nome ASC";
	
	final String getAllByGenere = "SELECT * FROM album ORDER BY genere ASC";
	
	final String getAllByInserimento = "SELECT * FROM album ORDER BY data_inserimento DESC;";	

	@Insert(insert)
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void insert(AlbumPOJO a);
	
	@Select(findById)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public AlbumPOJO findById (Long id);
	
	@Select(findDataAcquisto)
	public LocalDate findDataAcquisto (@Param("idUtente") Long idUtente, @Param("idAlbum") Long idALbum);
	
	@Select(getAlbumsByUtente)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAlbumsByUtente(Long id);
	
	@Select(getAlbumsByUtenteOrderNome)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAlbumsByUtenteOrderNome(Long id);
	
	@Select(getAlbumsByUtenteOrderGenere)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAlbumsByUtenteOrderGenere(Long id);
	
	@Select(getAlbumsByUtenteOrderUscita)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAlbumsByUtenteOrderUscita(Long id);
	
	@Select(getAlbumsByUtenteOrderArtista)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAlbumsByUtenteOrderArtista(Long id);

	@Select(getAll)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAll();

	@Select(getAllByArtista)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAllByArtista();

	@Select(getAllByUscita)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAllByUscita();

	@Select(getAllByNome)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAllByNome();

	@Select(getAllByGenere)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAllByGenere();

	@Select(getAllByInserimento)
	@Results(value = {
			@Result(property = "id",column = "id"),
			@Result(property = "nome",column = "nome"),
			@Result(property = "artista",column = "artista"),
			@Result(property = "annoUscita",column = "anno_uscita"),
			@Result(property = "genere",column = "genere"),
			@Result(property = "copertina",column = "copertina"),
			@Result(property = "dataInserimento",column = "data_inserimento")
	})
	public List<AlbumPOJO> getAllByInserimento();


}
