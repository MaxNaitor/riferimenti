package mappers;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.AlbumPOJO;

public interface AlbumMapper {

	public void insert(AlbumPOJO a);
	
	public AlbumPOJO getAlbum(Long id);
	
	public AlbumPOJO findById (Long id);
	
	public LocalDate findDataAcquisto (@Param("idUtente") Long idUtente, @Param("idAlbum") Long idALbum);

	public List<AlbumPOJO> getAlbumsByUtente(Long id);
	
	public List<AlbumPOJO> getAlbumsByUtenteOrderNome(Long id);
	
	public List<AlbumPOJO> getAlbumsByUtenteOrderGenere(Long id);
	
	public List<AlbumPOJO> getAlbumsByUtenteOrderUscita(Long id);
	
	public List<AlbumPOJO> getAlbumsByUtenteOrderArtista(Long id);

	public List<AlbumPOJO> getAll();

	public List<AlbumPOJO> getAllByArtista();

	public List<AlbumPOJO> getAllByUscita();

	public List<AlbumPOJO> getAllByNome();

	public List<AlbumPOJO> getAllByGenere();

	public List<AlbumPOJO> getAllByInserimento();


}
