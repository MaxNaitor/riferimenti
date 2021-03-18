//package services;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import dao.UtenteDAOTemplate;
//import exception.CredenzialiException;
//import model.UtenteTemplate;
//
//@Service
//public class UtenteServiceTemplate {
//
//	private UtenteDAOTemplate utenteDao;
//
//	@Autowired
//	public void setUtenteDao(UtenteDAOTemplate utenteDao) {
//		this.utenteDao = utenteDao;
//	}
//
//	public UtenteTemplate registrazione(UtenteTemplate utente) throws CredenzialiException {
//		try {
//			// registro l'utente e successivamente recupero il suo ID
//			utenteDao.registrazione(utente);
//			utente = accesso(utente);
//			// se l'utente esiste già,lancio l'eccezione
//		} catch (DataAccessException e) {
//			throw new CredenzialiException();
//		}
//		return utente;
//	}
//
//	public UtenteTemplate accesso(UtenteTemplate utente) throws CredenzialiException {
//		try {
//			utente = utenteDao.accesso(utente);
//		} catch (DataAccessException e) {
//			throw new CredenzialiException();
//		}
//		return utente;
//	}
//	
//	public ArrayList<UtenteTemplate> getListaUtenti (UtenteTemplate utente) {
//		return (ArrayList<UtenteTemplate>) utenteDao.listaUtenti(utente);
//	}
//	
//	public UtenteTemplate getUtente (int id) {
//		return utenteDao.getUtente(id);
//	}
//}
