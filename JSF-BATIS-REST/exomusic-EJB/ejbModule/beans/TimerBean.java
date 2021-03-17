package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import interfaces.AlbumLocal;
import interfaces.TimerBeanLocal;
import pojo.AlbumPOJO;
import pojo.TracciaPOJO;

@Stateless
public class TimerBean implements TimerBeanLocal {

//	@PersistenceContext(unitName = "exomusicPU")
//	private EntityManager em;

	@EJB
	private AlbumLocal albumService;

//	private List<Album> albums;

	private List<AlbumPOJO> albums;

	public TimerBean() {
//		albums = new ArrayList<Album>();
		albums = new ArrayList<AlbumPOJO>();
	}

	@Schedule(second = "*/30", minute = "*", hour = "8-23", dayOfWeek = "Mon-Fri", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
	private void scheduledTimeout(final Timer t) {
		if (!albums.isEmpty()) {
			albums.get(0).setDataInserimento(LocalDate.now());
			albumService.insert(albums.get(0));
			if (albums.get(0).getTracce() != null) {
				for (TracciaPOJO tr : albums.get(0).getTracce()) {
					tr.setIdAlbum(albums.get(0).getId());
					albumService.insert(tr);
				}
			}
			albums.remove(0);
		}
	}

	@Override
	public void aggiungi(AlbumPOJO a) {
		albums.add(a);
	}

//	@Schedule(second = "*/30", minute = "*", hour = "8-23", dayOfWeek = "Mon-Fri", dayOfMonth = "*", month = "*", year = "*", info = "MyTimer")
//	private void scheduledTimeout(final Timer t) {
//		if (albums.size() != 0) {
//			albums.get(0).setDataInserimento(LocalDate.now());
//			em.persist(albums.get(0));
//			getId(albums.get(0));
//			if (albums.get(0).getTracce() != null) {
//				for (Traccia tr : albums.get(0).getTracce()) {
//					tr.setAlbum(new Album(albums.get(0).getId()));
//					em.persist(tr);
//				}
//			}
//			albums.remove(0);
//		}
//	}
	
//	@Override
//	public void aggiungi(Album a) {
//		albums.add(a);
//	}

//	private void getId(Album album) {
//		album.setId((Long) em.createQuery("SELECT MAX(a.id) FROM Album a").getSingleResult());
//	}
}