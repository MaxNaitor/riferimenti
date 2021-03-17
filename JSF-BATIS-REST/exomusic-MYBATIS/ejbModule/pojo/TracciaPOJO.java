package pojo;


public class TracciaPOJO {

	private Long id;

	private String titolo;

	private String durata;

	private AlbumPOJO album;
	
	private Long idAlbum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public AlbumPOJO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumPOJO album) {
		this.album = album;
	}
	
	public Long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	@Override
	public String toString() {
		return "TracciaPOJO [id=" + id + ", titolo=" + titolo + ", durata=" + durata + ", album=" + album + "]";
	}

}
