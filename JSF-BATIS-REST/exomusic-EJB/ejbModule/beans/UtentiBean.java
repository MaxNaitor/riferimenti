package beans;

import java.util.Date;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;

import mappers.UtenteMapper;
import interfaces.AlbumLocal;
import interfaces.UtentiLocal;
import pojo.AlbumPOJO;
import pojo.UtentePOJO;
import util.SqlMapFactory;

@Stateless
public class UtentiBean implements UtentiLocal {

//	@PersistenceContext(unitName = "exomusicPU")
//	private EntityManager em;

	@EJB
	private AlbumLocal albumBean;
	
	@Override
	public void insert(UtentePOJO u) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			um.insert(u);
			inviaEmail(u);
		} finally {
			session.close();
		}
	}

	@Override
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
			u.setAcquistati(albumBean.getAlbumsByUtente(u.getId()));
			for (AlbumPOJO a : u.getAcquistati()) {
				albumBean.setRestituibile(a, u);
				a.setTracce(albumBean.findTracceByAlbum(a.getId()));
			}
			return u;
		} finally {
			session.close();
		}
	}

	@Override
	public void acquista(UtentePOJO u, AlbumPOJO a) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			um.acquista(u.getId(), a.getId());
		} finally {
			session.close();
		}
	}

	@Override
	public void restituisci(UtentePOJO u, AlbumPOJO a) {
		SqlSession session = SqlMapFactory.getSqlSessionFactory().openSession();
		try {
			UtenteMapper um = session.getMapper(UtenteMapper.class);
			um.restituisci(u.getId(), a.getId());
		} finally {
			session.close();
		}
	}

	private void inviaEmail(UtentePOJO u) {
		String to = u.getEmail();
		String from = "javaexomail@gmail.com";
		String host = "smtp.google.com";

		Properties props = new Properties();

		// specifico l'host a cui invio la mail
		props.put("mail.smtp.host", host);
		// imposto il debug
		props.put("mail.debug", "true");
		Session session = Session.getInstance(props);

		try {
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Registrazione a ExoMusic");
			msg.setSentDate(new Date());

			msg.setText("Ciao " + u.getNome() + "! La registrazione a ExoMusic è avvenuta con successo!");

			Transport.send(msg);
		} catch (MessagingException mex) {

		}
	}

//	@Override
//	public void insert(Utente u) throws Exception {
//		try {
//			em.persist(u);
//			inviaEmail(u);
//		} catch (Exception e) {
//			throw new Exception();
//		}
//	}
//
//	@Override
//	public Utente accesso(Utente u) {
//		try {
//			u = (Utente) em.createQuery("FROM Utente WHERE email = :email AND pass = :pass")
//					.setParameter("email", u.getEmail()).setParameter("pass", u.getPass()).getSingleResult();
//			List<Acquisto> acquisti = getAcquisti(u);
//			for (Album a : u.getAcquistati()) {
//				Period period = Period.between(LocalDate.now(), a.getDataInserimento());
//				Integer diff = period.getDays();
//				if (diff > -1) {
//					a.setNuovo(true);
//				}
//				for (Acquisto acq : acquisti) {
//					if (acq.getIdAlbum() == a.getId()) {
//						Period period2 = Period.between(LocalDate.now(), acq.getDataAcquisto());
//						Integer diff2 = period2.getDays();
//						if (diff2 > -1) {
//							a.setRestituibile(true);
//						}
//					}
//				}
//			}
//			return u;
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@Override
//	public void acquista(Utente u, Album a) {
//		Acquisto acquisto = new Acquisto(u.getId(), a.getId(), LocalDate.now());
//		em.persist(acquisto);
//	}
//
//	@Override
//	public void restituisci(Utente u, Album a) {
//		em.remove(getAcquisto(u, a));
//	}
//
//	@Override
//	public Utente get(Utente u) {
//		return em.find(Utente.class, u);
//	}
//
//	@Override
//	public void delete(Utente u) {
//		em.remove(u);
//	}
//
//	@Override
//	public List<Utente> getAll() {
//		return em.createQuery("FROM Utente").getResultList();
//	}
//
//	@Override
//	public void update(Utente u) {
//		em.merge(u);
//	}

//	@Override
//	public Acquisto getAcquisto(Utente u, Album a) {
//		return (Acquisto) em.createQuery("From Acquisto WHERE id_utente = :idU AND id_album = :idA")
//				.setParameter("idU", u.getId()).setParameter("idA", a.getId()).getSingleResult();
//	}
//
//	@Override
//	public List<Acquisto> getAcquisti(Utente u) {
//		return em.createQuery("From Acquisto WHERE id_utente = :id").setParameter("id", u.getId()).getResultList();
//	}
}
