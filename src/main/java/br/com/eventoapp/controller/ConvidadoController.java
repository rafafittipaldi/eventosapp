package br.com.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

	@PostMapping(value = "/incluirConvidado")
	public RedirectView incluirConvidado(Convidado convidado, RedirectAttributes attributes) {
		Evento evento = eventoRepository.getOne(convidado.getEvento().getCodigo());
		
		convidado.setEvento(evento);
		
		convidadoRepository.save(convidado);
		
		return new RedirectView("/detalharEvento/" + convidado.getEvento().getCodigo());
	} 
}
