//package dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCallback;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.core.RowMapper;
//
//import model.UtenteTemplate;
//
//public class UtenteDAOTemplate {
//
//	private JdbcTemplate template;
//
//	@Autowired
//	public void setTemplate(JdbcTemplate template) {
//		this.template = template;
//	}
//	
//	public Boolean registrazione (UtenteTemplate utente) {
//		String query = "INSERT INTO utenti (username,psw) VALUES (?,?)";
//		return template.execute(query, new PreparedStatementCallback<Boolean>() {
//			@Override
//			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//				ps.setString(1, utente.getUsername());
//				ps.setString(2, utente.getPassword());
//				return ps.execute();
//			}
//		});
//	}
//	
//	public UtenteTemplate accesso (UtenteTemplate persona) {
//		String query = "SELECT * FROM utenti WHERE username = ? AND psw = ?";
//		return template.queryForObject(query, new Object[] {persona.getUsername(),persona.getPassword()}, new BeanPropertyRowMapper<UtenteTemplate>(UtenteTemplate.class));
//	}
//	
//	public List<UtenteTemplate> listaUtenti (UtenteTemplate utente) {
//		PreparedStatementCreator ps = new PreparedStatementCreator() {
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE id <> ?");
//				ps.setInt(1, utente.getId());
//				return ps;
//			}
//		};
//		RowMapper <UtenteTemplate> mapper = new RowMapper<UtenteTemplate>() {
//			@Override
//			public UtenteTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
//				UtenteTemplate utente = new UtenteTemplate ();
//				utente.setId(rs.getInt("id"));
//				utente.setUsername(rs.getString("username"));
//				utente.setPassword(rs.getString("psw"));
//				return utente;
//			}
//		};
//		return template.query(ps, mapper);
//	}
//	
//	public UtenteTemplate getUtente (int id) {
//		String query = "SELECT * FROM utenti WHERE id = ?";
//		return template.queryForObject(query, new Object[] {id},new BeanPropertyRowMapper<UtenteTemplate>(UtenteTemplate.class));
//	}
//}
