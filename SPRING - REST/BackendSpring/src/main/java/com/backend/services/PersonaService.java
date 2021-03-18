package com.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Persona;
import com.backend.repositories.PersonaRepository;

@Service
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepo;
	
	public Persona insert (Persona p) {
		return personaRepo.save(p);
	}
	
	public List <Persona> getAll () {
		return (List<Persona>) personaRepo.findAll();
	}
	
	public Persona findById (Long id) {
		return personaRepo.findById(id).get();
	}
}
