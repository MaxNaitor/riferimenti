package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Post;
import model.Utente;
import repositories.PostRepo;

@Service
public class PostService {

	private PostRepo postRepo;

	@Autowired
	public void setPostRepo(PostRepo postRepo) {
		this.postRepo = postRepo;
	}

	public void pubblicaPost(Post post) {
		postRepo.save(post);
	}

	public ArrayList<Post> getPostUtente(Utente utente) {
		return (ArrayList<Post>) postRepo.findByUtente(utente.getId());
	}

	public void aggiungiLike(Post post) {
		post.aggiungiLike();
		postRepo.save(post);
	}

	public void eliminaPost(Post post) {
		postRepo.delete(post);
	}
}
