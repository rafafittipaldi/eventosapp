package br.com.eventoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eventoapp.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {}
