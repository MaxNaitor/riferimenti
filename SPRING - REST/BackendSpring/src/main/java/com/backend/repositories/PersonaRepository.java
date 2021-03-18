package com.backend.repositories;

import org.springframework.data.repository.CrudRepository;

import com.backend.entities.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long>{

}
