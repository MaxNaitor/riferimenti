//package model;
//
//import javax.validation.constraints.NotBlank;
//
//public class UtenteTemplate {
//
//	
//	private int id = 999;
//	//con l'annotazione NotBlank,possiamo effettuare le validazioni dei campi,utilizzando @Valid e Errors nel controller
//	@NotBlank(message="Errore: Username vuoto")
//	private String username;
//	@NotBlank(message="Errore: Password vuota")
//	private String password;
//	
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//}
