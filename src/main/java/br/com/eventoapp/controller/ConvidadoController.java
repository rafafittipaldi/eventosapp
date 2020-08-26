package br.com.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.eventoapp.model.Convidado;
import br.com.eventoapp.model.Evento;
import br.com.eventoapp.repository.ConvidadoRepository;
import br.com.eventoapp.repository.EventoRepository;

@Controller
public class ConvidadoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;

	@PostMapping(value = "/incluirConvidado/{codigoEvento}")
	public String incluirConvidado(@PathVariable("codigoEvento") Long codigoEvento, Convidado convidado) {
		Evento evento = eventoRepository.findById(codigoEvento).get();
		
		convidado.setEvento(evento);
		
		convidadoRepository.save(convidado);
		
		return "redirect:/detalharEvento/{codigoEvento}";
	} 
}
