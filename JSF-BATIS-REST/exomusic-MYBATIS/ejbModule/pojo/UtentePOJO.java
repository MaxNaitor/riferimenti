package pojo;

import java.time.LocalDate;
import java.util.List;

public class UtentePOJO {

	private Long id;

	private String nome;

	private String cognome;

	private LocalDate dataNascita;

	private String sesso;

	private String email;

	private String pass;

	private List<AlbumPOJO> acquistati;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<AlbumPOJO> getAcquistati() {
		return acquistati;
	}

	public void setAcquistati(List<AlbumPOJO> acquistati) {
		this.acquistati = acquistati;
	}

	@Override
	public String toString() {
		return "UtentePOJO [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", sesso=" + sesso + ", email=" + email + ", pass=" + pass + ", acquistati=" + acquistati + "]";
	}
	
	

}
