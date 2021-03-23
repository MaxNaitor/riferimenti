package pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AlbumPOJO {

	private Long id;

	private String nome;

	private String artista;

	private String annoUscita;

	private String genere;

	private String copertina;

	private LocalDate dataInserimento;

	private List<TracciaPOJO> tracce;
	
	private LocalDate dataAcquisto;

	private boolean acquistato = false;

	private boolean nuovo = false;

	private boolean restituibile = false;

	public void aggiungiTraccia(TracciaPOJO traccia) {
		if (this.tracce == null) {
			tracce = new ArrayList<TracciaPOJO>();
		}
		tracce.add(traccia);
	}

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

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getAnnoUscita() {
		return annoUscita;
	}

	public void setAnnoUscita(String annoUscita) {
		this.annoUscita = annoUscita;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getCopertina() {
		return copertina;
	}

	public void setCopertina(String copertina) {
		this.copertina = copertina;
	}

	public boolean isAcquistato() {
		return acquistato;
	}

	public void setAcquistato(boolean acquistato) {
		this.acquistato = acquistato;
	}

	public AlbumPOJO(Long id) {
		this.id = id;
	}

	public List<TracciaPOJO> getTracce() {
		return tracce;
	}

	public void setTracce(List<TracciaPOJO> tracce) {
		this.tracce = tracce;
	}

	public LocalDate getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDate dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public boolean isNuovo() {
		return nuovo;
	}

	public void setNuovo(boolean nuovo) {
		this.nuovo = nuovo;
	}

	public boolean isRestituibile() {
		return restituibile;
	}

	public void setRestituibile(boolean restituibile) {
		this.restituibile = restituibile;
	}
	
	
	public LocalDate getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(LocalDate dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public AlbumPOJO () {
		
	}
}
