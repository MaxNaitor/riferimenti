package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="segnalazioni")
public class Segnalazione {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_utente_segnalato")
	private Utente utenteSegnalato;
	
	@ManyToOne
	@JoinColumn(name="id_utente_segnalatore")
	private Utente utenteSegnalatore;
	
	@ManyToOne
	@JoinColumn(name="id_post_segnalato")
	private Post postSegnalato;
	
	@Column(name="motivazione")
	private String motivazione = "Motivazione Generica";
	
	@Column(name="esito")
	private String esito = "In Attesa";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utente getUtenteSegnalato() {
		return utenteSegnalato;
	}

	public void setUtenteSegnalato(Utente utenteSegnalato) {
		this.utenteSegnalato = utenteSegnalato;
	}

	public Utente getUtenteSegnalatore() {
		return utenteSegnalatore;
	}

	public void setUtenteSegnalatore(Utente utenteSegnalatore) {
		this.utenteSegnalatore = utenteSegnalatore;
	}

	public Post getPostSegnalato() {
		return postSegnalato;
	}

	public void setPostSegnalato(Post postSegnalato) {
		this.postSegnalato = postSegnalato;
	}

	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	

}
