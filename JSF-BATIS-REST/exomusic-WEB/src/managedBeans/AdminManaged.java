package managedBeans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import interfaces.TimerBeanLocal;
import pojo.AlbumPOJO;
import pojo.TracciaPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("adminModel")
@SessionScoped
public class AdminManaged implements Serializable {

	@EJB
	private TimerBeanLocal timerBean;

	private final String password = "AdminPassword";

	private String passwordInserita;

//	private Album album;
	
	private AlbumPOJO album;

//	private List<Traccia> tracce;
	
	private List<TracciaPOJO> tracce;

//	private Traccia traccia;
	
	private TracciaPOJO traccia;

	public String accesso() {
		if (passwordInserita.equals(password)) {
			return "inserimentoForm";
		}
		FacesContext.getCurrentInstance().addMessage("CredenzialiErrate",
				new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Credenziali Errate"));
		return logout();
	}

	public String inserimento() {
		timerBean.aggiungi(album);
		album = new AlbumPOJO();
		FacesContext.getCurrentInstance().addMessage("Inserimento",
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Album inserito con successo!"));
		return "inserimentoForm";
	}

	public void aggiungiTracce() {
		album.aggiungiTraccia(traccia);
		FacesContext.getCurrentInstance().addMessage("Inserimento",
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Traccia inserita con successo!"));
		traccia = new TracciaPOJO();
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "index";
	}

	public String getPasswordInserita() {
		return passwordInserita;
	}

	public void setPasswordInserita(String passwordInserita) {
		this.passwordInserita = passwordInserita;
	}

	public AlbumPOJO getAlbum() {
		return album;
	}

	public AdminManaged() {
		album = new AlbumPOJO();
		tracce = new ArrayList<TracciaPOJO>();
		traccia = new TracciaPOJO();
	}

	public void setAlbumPOJO(AlbumPOJO album) {
		this.album = album;
	}

	public TracciaPOJO getTraccia() {
		return traccia;
	}

	public void setTraccia(TracciaPOJO traccia) {
		this.traccia = traccia;
	}

	public List<TracciaPOJO> getTracce() {
		return tracce;
	}

	public void setTracce(List<TracciaPOJO> tracce) {
		this.tracce = tracce;
	}

	private static final long serialVersionUID = 1L;

}
