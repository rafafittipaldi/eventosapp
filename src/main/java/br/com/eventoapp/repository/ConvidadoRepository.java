package br.com.eventoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eventoapp.model.Convidado;
import br.com.eventoapp.model.Evento;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, String> {
	Iterable<Convidado> findByEvento(Evento evento);
}
