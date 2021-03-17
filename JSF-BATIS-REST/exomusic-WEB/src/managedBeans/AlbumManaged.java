package managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import interfaces.AlbumLocal;
import pojo.AlbumPOJO;

import java.io.Serializable;

@Named("albumModel")
@SessionScoped
public class AlbumManaged implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private AlbumLocal albumService;

	@Inject
	private UtentiManaged utenteBean;

//	private Album album;

	private AlbumPOJO album;

//	private List<Album> catalogo;

	private List<AlbumPOJO> catalogo;

	private String ordine;

	public String visualizzaNegozio() {
		switch (ordine) {
		case "nome":
			catalogo = albumService.getAllByNome();
			break;
		case "artista":
			catalogo = albumService.getAllByArtista();
			break;
		case "uscita":
			catalogo = albumService.getAllByUscita();
			break;
		case "genere":
			catalogo = albumService.getAllByGenere();
			break;
		case "inserimento":
			catalogo = albumService.getAllByInserimento();
			break;
		default:
			catalogo = albumService.getAllByInserimento();
		}

		for (AlbumPOJO a : catalogo) {
			albumService.setAcquistatoNuovo(a, utenteBean.getUtente());
		}
		ordine = " ";
		return "negozio";
	}

	public String visualizzaTracce() {
		album = albumService.getAlbum(Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumID")));
		return "utente?faces-redirect=true";
	}

	public String visualizzaTracceNegozio() {
		album = albumService.getAlbum(Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumID")));
		return "negozio?faces-redirect=true";
	}

	public AlbumManaged() {
//		album = new Album();
		album = new AlbumPOJO();
		ordine = " ";
	}

	public UtentiManaged getUtenteBean() {
		return utenteBean;
	}

	public void setUtenteBean(UtentiManaged utenteBean) {
		this.utenteBean = utenteBean;
	}

	public String getOrdine() {
		return ordine;
	}

	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public AlbumPOJO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumPOJO albumPOJO) {
		this.album = albumPOJO;
	}

	public List<AlbumPOJO> getCatalogo() {
		return catalogo;
	}

	public void setCatalogoPOJO(List<AlbumPOJO> catalogoPOJO) {
		this.catalogo = catalogoPOJO;
	}

//	public Album getAlbum() {
//	return album;
//}
//
//public void setAlbum(Album album) {
//	this.album = album;
//}

//public List<Album> getCatalogo() {
//	return catalogo;
//}
//
//public void setCatalogo(List<Album> catalogo) {
//	this.catalogo = catalogo;
//}

}
