package com.backend.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entities.Persona;
import com.backend.services.PersonaService;

@RestController
@RequestMapping("/provaRest")
public class RestPersona {
	
	@Autowired
	private PersonaService personaService;
	
	@PostMapping(path="/inserisci",consumes = "application/json")
	public void inserisciPersona (@RequestBody Persona persona) {
		personaService.insert(persona);
	}
	
	@GetMapping("/getAll")
	public List <Persona> getAll () {
		return personaService.getAll();
	}
	
	@GetMapping("/{id}")
	public Persona findById (@PathVariable("id")Long id) {
		return personaService.findById(id);
	}

}
