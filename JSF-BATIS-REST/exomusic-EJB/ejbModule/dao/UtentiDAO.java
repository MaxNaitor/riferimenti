package dao;


import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import mappers.UtenteMapper;
import pojo.UtentePOJO;
import util.SqlMapFactory;

public class UtentiDAO {

	private static UtentiDAO instance = null;

	private UtentiDAO() {

	}

	public static UtentiDAO getInstance() {
		if (instance == null) {
			instance = new UtentiDAO();
		}
		return instance;
	}

	public UtentePOJO accesso(UtentePOJO u) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			if (u.getId() == null) {
				u = um.accesso(u);
				if (u == null) {
					return null;
				}
			}
			u.setAcquistati(AlbumDAO.getInstance().getAlbumsByUtente(u.getId()));
			return u;
		} finally {
			session.close();
		}
	}

	public void registrazione(UtentePOJO u) throws PersistenceException {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			um.insert(u);
		} finally {
			session.close();
		}
	}

	public void acquista(Long idUtente, Long idAlbum) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			um.acquista(idUtente, idAlbum);
		} finally {
			session.close();
		}
	}

	public void restituisci(Long idUtente, Long idAlbum) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			um.restituisci(idUtente, idAlbum);
		} finally {
			session.close();
		}
	}

}
