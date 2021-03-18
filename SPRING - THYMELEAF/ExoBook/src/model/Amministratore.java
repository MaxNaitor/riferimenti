package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="amministratori")
public class Amministratore {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username")
	@NotBlank(message="Errore: Username vuoto")
	private String username;
	@Column(name="psw")
	@NotBlank(message="Errore: Password vuota")
	private String password;
	@Column(name="codice_sicurezza")
	@NotBlank(message="Errore: Inserire codice di sicurezza")
	private String codiceSicurezza;
	@Column(name="rango")
	private String rango = "Moderatore";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCodiceSicurezza() {
		return codiceSicurezza;
	}
	public void setCodiceSicurezza(String codiceSicurezza) {
		this.codiceSicurezza = codiceSicurezza;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	
	
	
	
}
