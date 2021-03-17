package managedBeans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import beans.Log4JLogger;
import interfaces.AlbumLocal;
import interfaces.UtentiLocal;
import pojo.AlbumPOJO;
import pojo.UtentePOJO;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named("utentiModel")
@SessionScoped
public class UtentiManaged implements Serializable {

	@EJB
	private UtentiLocal utentiService;

	@EJB
	private AlbumLocal albumService;
	
	@EJB
	private Log4JLogger logger;

//	private Utente utente;

	private UtentePOJO utente;

	public String accedi() {

		if (!validazioneEmail(utente.getEmail())) {
			FacesContext.getCurrentInstance().addMessage("Email",
					new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Email non valida!"));
			logger.getLogger().warn("Inserita email non valida");
			return logout();
		}
		codificaPassword(utente);
		utente = utentiService.accesso(utente);
		if (utente == null) {
			FacesContext.getCurrentInstance().addMessage("CredenzialiErrate",
					new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Credenziali Errate"));
			logger.getLogger().warn("Inserite credenziali non valide");
			return logout();
		}
		logger.getLogger().info("Utente " + utente.getId() + " ha effettuato l'accesso");
		return "utente?faces-redirect=true";
	}

	public String homepage() { 
		utente = utentiService.accesso(utente);
		return "utente";
	}

	public String registrazione() {
		try {
			if (!validazioneEmail(utente.getEmail())) {
				FacesContext.getCurrentInstance().addMessage("Email",
						new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Email non valida!"));
				return logout();
			}
			codificaPassword(utente);
			utentiService.insert(utente);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("Email Registrata",
					new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Email già Registrata"));
			return logout();
		}
		return homepage();
	}

	public String acquista() {
		AlbumPOJO album = new AlbumPOJO(Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumID")));
		album.setNome(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumNOME"));
		utentiService.acquista(utente, album);
		FacesContext.getCurrentInstance().addMessage("Acquisto Avvenuto", new FacesMessage(FacesMessage.SEVERITY_INFO,
				null, "Acquisto di " + album.getNome() + " avvenuto con successo!"));
		return homepage();
	}

	public String restituisci() {
		AlbumPOJO album = new AlbumPOJO(Long.parseLong(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumID")));
		album.setNome(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumNOME"));
		utentiService.restituisci(utente, album);
		FacesContext.getCurrentInstance().addMessage("Restituzione",
				new FacesMessage(FacesMessage.SEVERITY_INFO, null, album.getNome() + " è stato restituito!"));
		return homepage();
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		utente = new UtentePOJO();
		return "index";
	}

	private void codificaPassword(UtentePOJO u) {
		String password = u.getPass();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String codPass = number.toString(16);
			u.setPass(codPass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	private boolean validazioneEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public UtentiManaged() {
//		utente = new Utente();
		utente = new UtentePOJO();
	}

	public UtentePOJO getUtente() {
		return utente;
	}

	public void setUtente(UtentePOJO utentePOJO) {
		this.utente = utentePOJO;
	}

//	public Utente getUtente() {
//		return utente;
//	}
//
//	public void setUtente(Utente utente) {
//		this.utente = utente;
//	}

	private static final long serialVersionUID = 1L;
}
