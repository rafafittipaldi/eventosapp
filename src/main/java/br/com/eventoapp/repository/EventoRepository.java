package br.com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.eventoapp.model.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {}
