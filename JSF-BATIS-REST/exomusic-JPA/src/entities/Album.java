package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "album")
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "artista")
	private String artista;

	@Column(name = "anno_uscita")
	private String annoUscita;

	@Column(name = "genere")
	private String genere;

	@Column(name = "copertina")
	private String copertina;

	@Column(name = "data_inserimento")
	private LocalDate dataInserimento;

	@OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
	private List<Traccia> tracce;

	@Transient
	private boolean acquistato = false;

	@Transient
	private boolean nuovo = false;

	@Transient
	private boolean restituibile = false;

	public void aggiungiTraccia(Traccia traccia) {
		if (this.tracce == null) {
			tracce = new ArrayList<Traccia>();
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

	public Album(Long id) {
		this.id = id;
	}

	public List<Traccia> getTracce() {
		return tracce;
	}

	public void setTracce(List<Traccia> tracce) {
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

	public Album() {

	}

}
