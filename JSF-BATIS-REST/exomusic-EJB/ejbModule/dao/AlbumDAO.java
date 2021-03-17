package dao;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mappers.AlbumMapper;
import mappers.TracciaMapper;
import pojo.AlbumPOJO;
import pojo.TracciaPOJO;
import util.SqlMapFactory;

public class AlbumDAO {

	private static AlbumDAO instance = null;

	private AlbumDAO() {

	}

	public static AlbumDAO getInstance() {
		if (instance == null) {
			instance = new AlbumDAO();
		}
		return instance;
	}

	public void salva(AlbumPOJO a) {
		a.setDataInserimento(LocalDate.now());
		insert(a);
		if (!a.getTracce().isEmpty() || a.getTracce() != null) {
			for (TracciaPOJO tr : a.getTracce()) {
				tr.setIdAlbum(a.getId());
				insert(tr);
			}
		}
	}

	public void insert(AlbumPOJO a) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			am.insert(a);
		} finally {
			session.close();
		}
	}

	public void insert(TracciaPOJO t) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			TracciaMapper tm = session.getMapper(TracciaMapper.class);
			tm.insert(t);
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAlbumsByUtente(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> lista = am.getAlbumsByUtente(id);
			getTracceSetRestituibile(lista, id);
			return lista;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAlbumsByUtenteOrderNome(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> lista = am.getAlbumsByUtenteOrderNome(id);
			getTracceSetRestituibile(lista, id);
			return lista;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAlbumsByUtenteOrderGenere(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> lista = am.getAlbumsByUtenteOrderGenere(id);
			getTracceSetRestituibile(lista, id);
			return lista;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAlbumsByUtenteOrderUscita(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> lista = am.getAlbumsByUtenteOrderUscita(id);
			getTracceSetRestituibile(lista, id);
			return lista;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAlbumsByUtenteOrderArtista(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> lista = am.getAlbumsByUtenteOrderArtista(id);
			getTracceSetRestituibile(lista, id);
			return lista;
		} finally {
			session.close();
		}
	}

	public List<TracciaPOJO> findTracceByAlbum(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			TracciaMapper tm = session.getMapper(TracciaMapper.class);
			return tm.findTracceByAlbum(id);
		} finally {
			session.close();
		}
	}
	
	public AlbumPOJO getById(Long id) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			AlbumPOJO album = am.findById(id);
			getTracceSetNuovo(Arrays.asList(album));
			return album;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAll() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAll();
			getTracceSetNuovo(catalogo);
			return catalogo;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAllByArtista() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByArtista();
			getTracceSetNuovo(catalogo);
			return catalogo;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAllByUscita() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByUscita();
			getTracceSetNuovo(catalogo);
			return catalogo;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAllByNome() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByNome();
			getTracceSetNuovo(catalogo);
			return catalogo;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAllByGenere() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByGenere();
			getTracceSetNuovo(catalogo);
			return catalogo;
		} finally {
			session.close();
		}
	}

	public List<AlbumPOJO> getAllByInserimento() {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			List<AlbumPOJO> catalogo = am.getAllByInserimento();
			getTracceSetNuovo(catalogo);
			return catalogo;
		} finally {
			session.close();
		}
	}

	private void setRestituibile(AlbumPOJO a, Long idUtente) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			AlbumMapper am = session.getMapper(AlbumMapper.class);
			Period period = Period.between(LocalDate.now(), am.findDataAcquisto(idUtente, a.getId()));
			Integer diff = period.getDays();
			if (diff > -1) {
				a.setRestituibile(true);
			}
		} finally {
			session.close();
		}
	}

	private void setNuovo(AlbumPOJO a) {
		Period period = Period.between(LocalDate.now(), a.getDataInserimento());
		Integer diffDays = period.getDays();
		Integer diffMonths = period.getMonths();
		Integer diffYears = period.getYears();
		if (diffDays > -7 && diffMonths == 0 && diffYears == 0) {
			a.setNuovo(true);
		}
	}

	private void getTracceSetNuovo(List<AlbumPOJO> lista) {
		for (AlbumPOJO a : lista) {
			setNuovo(a);
			a.setTracce(findTracceByAlbum(a.getId()));
		}
	}

	private void getTracceSetRestituibile(List<AlbumPOJO> lista, Long idUtente) {
		for (AlbumPOJO a : lista) {
			setRestituibile(a, idUtente);
			a.setTracce(findTracceByAlbum(a.getId()));
		}
	}
}
