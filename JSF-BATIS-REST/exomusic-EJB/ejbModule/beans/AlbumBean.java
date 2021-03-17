package beans;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.ibatis.session.SqlSession;

import interfaces.AlbumLocal;
import mappers.AlbumMapper;
import mappers.TracciaMapper;
import pojo.AlbumPOJO;
import pojo.TracciaPOJO;
import pojo.UtentePOJO;
import util.SqlMapFactory;

@Stateless
public class AlbumBean implements AlbumLocal {

//	@PersistenceContext(unitName = "exomusicPU")
//	private EntityManager em;

	@Override
	public void insert(AlbumPOJO a) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			am.insert(a);
		} finally {
			session.close();
		}
	}

	// @Override
	public List<AlbumPOJO> getAlbumsByUtente(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			return am.getAlbumsByUtente(id);
		} finally {
			session.close();
		}
	}

	@Override
	public List<TracciaPOJO> findTracceByAlbum(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			TracciaMapper tm = session.getMapper(TracciaMapper.class);
			return tm.findTracceByAlbum(id);
		} finally {
			session.close();
		}
	}

	@Override
	public AlbumPOJO getAlbum(Long id) {
		System.out.println("entro nel metodo");
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			AlbumPOJO album = am.getAlbum(id);
			album.setTracce(findTracceByAlbum(id));
			return album;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AlbumPOJO> getAll() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAll();
			for (AlbumPOJO a : catalogo) {
				a.setTracce(findTracceByAlbum(a.getId()));
			}
			return catalogo;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AlbumPOJO> getAllByArtista() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByArtista();
			for (AlbumPOJO a : catalogo) {
				a.setTracce(findTracceByAlbum(a.getId()));
			}
			return catalogo;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AlbumPOJO> getAllByUscita() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByUscita();
			for (AlbumPOJO a : catalogo) {
				a.setTracce(findTracceByAlbum(a.getId()));
			}
			return catalogo;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AlbumPOJO> getAllByNome() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByNome();
			for (AlbumPOJO a : catalogo) {
				a.setTracce(findTracceByAlbum(a.getId()));
			}
			return catalogo;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AlbumPOJO> getAllByGenere() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByGenere();
			for (AlbumPOJO a : catalogo) {
				a.setTracce(findTracceByAlbum(a.getId()));
			}
			return catalogo;
		} finally {
			session.close();
		}
	}

	@Override
	public List<AlbumPOJO> getAllByInserimento() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByInserimento();
			for (AlbumPOJO a : catalogo) {
				a.setTracce(findTracceByAlbum(a.getId()));
			}
			return catalogo;
		} finally {
			session.close();
		}
	}

	@Override
	public void setAcquistatoNuovo(AlbumPOJO a, UtentePOJO u) {
		Period period = Period.between(LocalDate.now(), a.getDataInserimento());
		Integer diff = period.getDays();
		if (diff > -1) {
			a.setNuovo(true);
		}
		for (int i = 0; i < u.getAcquistati().size(); i++) {
			if (u.getAcquistati().get(i).getId() == a.getId()) {
				a.setAcquistato(true);
			}
		}
	}

	@Override
	public void setRestituibile(AlbumPOJO a, UtentePOJO u) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			Period period = Period.between(LocalDate.now(), am.findDataAcquisto(u.getId(), a.getId()));
			Integer diff = period.getDays();
			if (diff > -1) {
				a.setRestituibile(true);
			}
		} finally {
			session.close();
		}
	}

	@Override
	public void insert(TracciaPOJO t) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			TracciaMapper tm = session.getMapper(TracciaMapper.class);
			tm.insert(t);
		} finally {
			session.close();
		}
	}

//	@Override
//	public List<Album> getAll() {
//		return em.createQuery("From Album").getResultList();
//	}
//
//	@Override
//	public Album getAlbum(Long id) {
//		return em.find(Album.class, id);
//	}
//
//	@Override
//	public List<Album> getAllByArtista() {
//		return em.createQuery("From Album ORDER BY artista ASC").getResultList();
//	}
//
//	@Override
//	public List<Album> getAllByUscita() {
//		return em.createQuery("From Album ORDER BY anno_uscita DESC").getResultList();
//	}
//
//	@Override
//	public List<Album> getAllByNome() {
//		return em.createQuery("From Album ORDER BY nome ASC").getResultList();
//	}
//
//	@Override
//	public List<Album> getAllByGenere() {
//		return em.createQuery("From Album ORDER BY genere ASC").getResultList();
//	}
//
//	@Override
//	public List<Album> getAllByInserimento() {
//		return em.createQuery("From Album ORDER BY data_inserimento DESC").getResultList();
//	}

}
