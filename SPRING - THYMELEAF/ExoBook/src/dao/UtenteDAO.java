//package dao;
//
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import model.Utente;
//
//@Repository
//public class UtenteDAO implements IDAO<Utente> {
//	
//	private SessionFactory sessionFactory;
//	private Session session;
//	
//	@Autowired
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//
//	@Override
//	@Transactional
//	public void insert(Utente utente) {
//		session = sessionFactory.getCurrentSession();
//		session.persist(utente);		
//	}
//
//	@Override
//	@Transactional
//	public void update(Utente utente) {
//		session = sessionFactory.getCurrentSession();
//		session.update(utente);	
//		
//	}
//
//	@Override
//	@Transactional
//	public void delete(Utente utente) {
//		session = sessionFactory.getCurrentSession();
//		session.delete(utente);			
//	}
//
//	@Override
//	@Transactional
//	public Utente get(int id) {
//		session = sessionFactory.getCurrentSession();
//		return (Utente) session.get(Utente.class, id);
//	}
//	
//	@Transactional
//	public Utente get(Utente utente) {
//		session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("FROM Utente WHERE username = :us AND psw = :pw");
//		query.setParameter("us", utente.getUsername());
//		query.setParameter("pw", utente.getPassword());
//		return (Utente) query.uniqueResult();
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Transactional
//	public List<Utente> findAll (Utente utente) {
//		session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("From Utente WHERE id <> :id");
//		query.setParameter("id", utente.getId());
//		return query.list();
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Transactional
//	public List<Utente> findAll () {
//		session = sessionFactory.getCurrentSession();
//		return  session.createQuery("From Utente").list();
//	}
//}
