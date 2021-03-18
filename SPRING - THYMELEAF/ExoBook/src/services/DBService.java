//package services;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Properties;
//
//import org.springframework.stereotype.Service;
//
//import exception.CredenzialiException;
//import model.Messaggio;
//
//@Service
//public class DBService {
//
////	private static DBService instance = null;
//
//	String url = "jdbc:mysql://localhost:3306/crudspring?useUnicode=true"
//			+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
//
//	Connection conn;
//	PreparedStatement pStatement;
//	Statement statement;
//	ResultSet rs;
//
//	public void registrati(UtenteTemplate utente) throws CredenzialiException {
//		try {
//			conn = getConnection();
//			pStatement = conn.prepareStatement("SELECT * FROM utenti WHERE username = ?");
//			pStatement.setString(1, utente.getUsername());
//			rs = pStatement.executeQuery();
//			if (rs.next()) {
//				throw new CredenzialiException();
//			}
//			pStatement = conn.prepareStatement("INSERT INTO utenti (username,psw) VALUES (?,?)");
//			pStatement.setString(1, utente.getUsername());
//			pStatement.setString(2, utente.getPassword());
//			pStatement.execute();
//			pStatement = conn.prepareStatement("SELECT id FROM utenti WHERE username = ? AND psw = ?");
//			pStatement.setString(1, utente.getUsername());
//			pStatement.setString(2, utente.getPassword());
//			rs = pStatement.executeQuery();
//			if (rs.next()) {
//				utente.setId(rs.getInt("id"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//	}
//
//	public ArrayList<UtenteTemplate> getUtenti(UtenteTemplate utente) {
//		ArrayList<UtenteTemplate> utenti = new ArrayList<UtenteTemplate>();
//		try {
//			conn = getConnection();
//			pStatement = conn.prepareStatement("SELECT * FROM utenti WHERE id <> ?");
//			pStatement.setInt(1, utente.getId());
//			rs = pStatement.executeQuery();
//			while (rs.next()) {
//				UtenteTemplate u = new UtenteTemplate();
//				u.setId(rs.getInt("id"));
//				u.setUsername(rs.getString("username"));
//				u.setPassword(rs.getString("psw"));
//				utenti.add(u);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//		return utenti;
//	}
//
//	public UtenteTemplate getUtente(int id) {
//		UtenteTemplate utente = new UtenteTemplate();
//		try {
//			conn = getConnection();
//			pStatement = conn.prepareStatement("SELECT * FROM utenti WHERE id = ?");
//			pStatement.setInt(1, id);
//			rs = pStatement.executeQuery();
//			if (rs.next()) {
//				utente.setId(rs.getInt("id"));
//				utente.setUsername(rs.getString("username"));
//				utente.setPassword(rs.getString("psw"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//		return utente;
//	}
//
//	public void accesso(UtenteTemplate utente) throws CredenzialiException {
//		try {
//			conn = getConnection();
//			pStatement = conn.prepareStatement("SELECT id FROM utenti WHERE username = ? AND psw = ?");
//			pStatement.setString(1, utente.getUsername());
//			pStatement.setString(2, utente.getPassword());
//			rs = pStatement.executeQuery();
//			if (rs.next()) {
//				utente.setId(rs.getInt("id"));
//			} else
//				throw new CredenzialiException();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//	}
//
//	public void inviaMessaggio(Messaggio messaggio) {
//		try {
//			conn = getConnection();
//			pStatement = conn
//					.prepareStatement("INSERT INTO messaggi (id_mittente,id_destinatario,messaggio,stato) VALUES (?,?,?,?)");
//			pStatement.setInt(1, messaggio.getIdMittente());
//			pStatement.setInt(2, messaggio.getIdDestinatario());
//			pStatement.setString(3, messaggio.getMessaggio());
//			pStatement.setString(4, "Non Letto");
//			pStatement.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//	}
//	
//	public ArrayList<Messaggio> getMessaggiRicevuti (UtenteTemplate utente) {
//		ArrayList<Messaggio> messaggi = new ArrayList<Messaggio>();
//		try {
//			conn = getConnection();
//			pStatement = conn.prepareStatement("SELECT * FROM messaggi WHERE id_destinatario = ? AND stato = ?");
//			pStatement.setInt(1, utente.getId());
//			pStatement.setString(2, "Non Letto");
//			ResultSet rs2 = pStatement.executeQuery();
//			while (rs2.next()) {
//				Messaggio messaggio = new Messaggio();
//				messaggio.setId(rs2.getInt("id"));
//				messaggio.setIdMittente(rs2.getInt("id_mittente"));
//				messaggio.setIdDestinatario(rs2.getInt("id_destinatario"));
//				messaggio.setMessaggio(rs2.getString("messaggio"));
//				messaggio.setStato(rs2.getString("stato"));
////				messaggio.setMittente(getUtente(rs2.getInt("id_mittente")));
//				messaggi.add(messaggio);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//		return messaggi;
//	}
//	
//	public void segnaComeLetto (Messaggio messaggio) {
//		try {
//			conn = getConnection();
//			pStatement = conn.prepareStatement("UPDATE messaggi SET stato = ? WHERE id = ?");
//			pStatement.setString(1, "Letto");
//			pStatement.setInt(2, messaggio.getId());
//			pStatement.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			chiudiConnessioneDB();
//		}
//	}
//
//	void chiudiConnessioneDB() {
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	Connection getConnection() throws SQLException {
//		Properties props = new Properties();
//		props.setProperty("user", "root");
//		props.setProperty("password", "");
//		return DriverManager.getConnection(url, props);
//	}
////
////	private DBService() {
////
////	}
////
////	public static DBService getInstance() {
////		if (instance == null) {
////			instance = new DBService();
////		}
////		return instance;
////	}
//}
