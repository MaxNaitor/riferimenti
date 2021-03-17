package restBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.User;

@Path("/users")
@LocalBean
@Stateless
public class UsersRedux {

	@PersistenceContext(unitName = "exomusicPU")
	private EntityManager em;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void signUp(User user) {
		em.persist(user);
	}

	@POST
	@Path("/signin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User signin(User user) {
		return (User) em.createQuery("From User WHERE email = :email AND password = :password")
				.setParameter("email", user.getEmail()).setParameter("password", user.getPassword()).getSingleResult();
	}
}
