package interfaces;

import java.util.List;

import javax.ejb.Local;

import pojo.AlbumPOJO;
import pojo.TracciaPOJO;
import pojo.UtentePOJO;

@Local
public interface AlbumLocal {

	public List<TracciaPOJO> findTracceByAlbum(Long id);

	public void insert(AlbumPOJO a);

	public void insert(TracciaPOJO t);

	public AlbumPOJO getAlbum(Long id);

	public void setAcquistatoNuovo(AlbumPOJO a, UtentePOJO u);

	public void setRestituibile(AlbumPOJO a, UtentePOJO u);

	public List<AlbumPOJO> getAlbumsByUtente(Long id);

	public List<AlbumPOJO> getAll();

	public List<AlbumPOJO> getAllByArtista();

	public List<AlbumPOJO> getAllByUscita();

	public List<AlbumPOJO> getAllByNome();

	public List<AlbumPOJO> getAllByGenere();

	public List<AlbumPOJO> getAllByInserimento();

	// METODI JPA ----------------------------

//	public void inserisci(Album a); // batis

//	public List<Album> getAll(); // batis
//
//	public List<Album> getAllByArtista(); // batis
//
//	public List<Album> getAllByUscita(); // batis
//
//	public List<Album> getAllByNome(); // batis
//
//	public List<Album> getAllByGenere(); // batis
//
//	public List<Album> getAllByInserimento(); // batis

//	public Album getAlbum(Long id); // batis

}
