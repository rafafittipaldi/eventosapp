package br.com.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.eventoapp.model.Evento;
import br.com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping(value = "/cadastrarEvento")
	public String formEvento() {
		return "evento/formEvento";
	}
	
	@PostMapping(value = "/incluirEvento")
	public String incluirEvento(Evento evento) {
		
		eventoRepository.save(evento);
		
		return "redirect:/cadastrarEvento";
	}
	
	@GetMapping(value = "/listarEventos")
	public ModelAndView listarEventos() {
		ModelAndView     mv      = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		
		mv.addObject("eventos", eventos);
		
		return mv;
	}
	
	@GetMapping(value = "/detalharEvento/{codigo}")
	public ModelAndView detalharEvento(@PathVariable("codigo") Long codigo) {
		ModelAndView mv = new ModelAndView("evento/detalheEvento");
		
		Evento evento = eventoRepository.findById(codigo).get();
		
		mv.addObject("evento", evento);
		
		return mv;
	}
}
