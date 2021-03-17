package restBeans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entities.Project;

@Stateless
@LocalBean
@Path("/projects")
@SuppressWarnings("unchecked")
public class ProjectRedux {

	@PersistenceContext(unitName = "exomusicPU")
	private EntityManager em;
  
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAll () {
		return em.createQuery("From Project").getResultList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert (Project p) {
		em.persist(p);
	}
	
	
	@DELETE
	@Path("/{id}")
	public void delete (@PathParam("id") Long id) {
		Project p = em.find(Project.class, id);
		em.remove(p);

	}
	
	
    public ProjectRedux() {
    }

}
