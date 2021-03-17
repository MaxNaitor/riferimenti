package restBeans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.ibatis.exceptions.PersistenceException;

import dao.AlbumDAO;
import dao.UtentiDAO;
import pojo.AlbumPOJO;
import pojo.UtentePOJO;

@Stateless
@Path("/utenti")
public class UtentiService {

	@POST
	@Path("/accesso")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response accesso(UtentePOJO u) {
		if (!validazioneEmail(u.getEmail())) {
			return Response.serverError().status(Response.Status.NOT_ACCEPTABLE)
					.entity("Email non valida!\nRispetta il pattern xxx@xxx.xxx").build();
		}
		codificaPassword(u);
		u = UtentiDAO.getInstance().accesso(u);
		if (u == null) {
			return Response.serverError().status(Response.Status.CONFLICT).entity("Credenziali Errate!").build();
		}
		return Response.ok(u).build();
	}

	@POST
	@Path("/registrazione")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrazione(UtentePOJO u) {
		if (!validazioneEmail(u.getEmail())) {
			return Response.serverError().status(Response.Status.NOT_ACCEPTABLE)
					.entity("Email non valida!\nRispetta il pattern xxx@xxx.xxx").build();
		}
		codificaPassword(u);
		try {
			UtentiDAO.getInstance().registrazione(u);
			inviaEmail(u);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return Response.serverError().status(Response.Status.CONFLICT).entity("Email già registrata!").build();
		}		
		return Response.ok(u).build();
	}
	
	@GET
	@Path("/{id}/{ordine}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AlbumPOJO> getAlbumUtente (@PathParam("id") Long idUtente, @PathParam("ordine") String ordine) {
		switch (ordine) {
		case "genere":
			return AlbumDAO.getInstance().getAlbumsByUtenteOrderGenere(idUtente);
		case "inserimento":
			return AlbumDAO.getInstance().getAlbumsByUtente(idUtente);
		case "nome":
			return AlbumDAO.getInstance().getAlbumsByUtenteOrderNome(idUtente);
		case "uscita":
			return AlbumDAO.getInstance().getAlbumsByUtenteOrderUscita(idUtente);
		case "artista":
			return AlbumDAO.getInstance().getAlbumsByUtenteOrderArtista(idUtente);
		}
		return AlbumDAO.getInstance().getAlbumsByUtente(idUtente);
	}

	private void codificaPassword(UtentePOJO u) {
		String password = u.getPass();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String codPass = number.toString(16);
			u.setPass(codPass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
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

	private boolean validazioneEmail(String email) {
		Pattern pattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
