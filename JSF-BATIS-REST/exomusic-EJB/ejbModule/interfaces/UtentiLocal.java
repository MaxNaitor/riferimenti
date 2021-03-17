package interfaces;


import javax.ejb.Local;

import pojo.AlbumPOJO;
import pojo.UtentePOJO;

@Local
public interface UtentiLocal {

	public void insert(UtentePOJO u);

	public UtentePOJO accesso(UtentePOJO u);

	public void acquista(UtentePOJO u, AlbumPOJO a);

	public void restituisci(UtentePOJO u, AlbumPOJO a);

	// METODI JPA-----------------------------------------------------------

//	public void insert(Utente u) throws Exception; // batis
//
//	public Utente accesso(Utente u); // batis
//
//	public void acquista(Utente u, Album a); // BATIS
//
//	public void restituisci(Utente u, Album a); // batis
//
//	public Acquisto getAcquisto(Utente u, Album a);
//
//	public List<Acquisto> getAcquisti(Utente u);

}
