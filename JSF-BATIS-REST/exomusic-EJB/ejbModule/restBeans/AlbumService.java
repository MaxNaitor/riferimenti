package restBeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.AlbumDAO;
import dao.UtentiDAO;
import pojo.AcquistoPOJO;
import pojo.AlbumPOJO;

@Stateless
@Path("/albums")
public class AlbumService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void salva (AlbumPOJO a) {
		AlbumDAO.getInstance().salva(a);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AlbumPOJO> getAll() {
		return AlbumDAO.getInstance().getAll();
	}

	@GET
	@Path("/{ordine}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AlbumPOJO> getAllOrdered(@PathParam("ordine") String ordine) {
		switch (ordine) {
		case "genere":
			return AlbumDAO.getInstance().getAllByGenere();
		case "inserimento":
			return AlbumDAO.getInstance().getAllByInserimento();
		case "nome":
			return AlbumDAO.getInstance().getAllByNome();
		case "uscita":
			return AlbumDAO.getInstance().getAllByUscita();
		case "artista":
			return AlbumDAO.getInstance().getAllByArtista();
		}
		return AlbumDAO.getInstance().getAll();

	}

	@POST
	@Path("/acquista")
	@Consumes(MediaType.APPLICATION_JSON)
	public AlbumPOJO acquistaAlbum(AcquistoPOJO a) {
		UtentiDAO.getInstance().acquista(a.getIdUtente(), a.getIdAlbum());
		return AlbumDAO.getInstance().getById(a.getIdAlbum());
	}

	@POST
	@Path("/restituisci")
	@Consumes(MediaType.APPLICATION_JSON)
	public void restituisciAlbum(AcquistoPOJO a) {
		UtentiDAO.getInstance().restituisci(a.getIdUtente(), a.getIdAlbum());
	}

}
