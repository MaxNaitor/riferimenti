package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import exception.AdminException;
import exception.CredenzialiException;
import model.Amministratore;
import repositories.AdminRepo;

@Service
public class AdminService {

	private AdminRepo adminRepo;

	@Autowired
	public void setAdminRepo(AdminRepo adminRepo) {
		this.adminRepo = adminRepo;
	}

	public void nominaAdmin(Amministratore admin) throws AdminException {
		try {
			adminRepo.save(admin);
		} catch (DataIntegrityViolationException e) {
			throw new AdminException();
		}
	}

	public Amministratore accesso(Amministratore admin) throws CredenzialiException {
		admin = adminRepo.findByUsernameAndPasswordAndCodiceSicurezzaAndRango(admin.getUsername(), admin.getPassword(),
				admin.getCodiceSicurezza(), admin.getRango());
		if (admin == null) {
			throw new CredenzialiException();
		}
		return admin;
	}

}
