package pojo;

public class AcquistoPOJO {

	private Long idUtente;
	
	private Long idAlbum;

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public Long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	@Override
	public String toString() {
		return "AcquistoPOJO [idUtente=" + idUtente + ", idAlbum=" + idAlbum + "]";
	}
	
	
	
	
}
