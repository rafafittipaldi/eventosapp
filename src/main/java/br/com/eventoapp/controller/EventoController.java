package br.com.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import br.com.eventoapp.model.Convidado;
import br.com.eventoapp.model.Evento;
import br.com.eventoapp.repository.ConvidadoRepository;
import br.com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;

	@GetMapping(value = "/cadastrarEvento")
	public String formEvento() {
		return "evento/formEvento";
	}
	
	@PostMapping(value = "/incluirEvento")
	public RedirectView incluirEvento(@Valid Evento evento, 
			BindingResult result, 
			RedirectAttributes attributes) {
		
		RedirectView rv = new RedirectView("/cadastrarEvento");
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagemError", "Verifique os campos obrigatórios!");
			return rv;
		}
		
		eventoRepository.save(evento);
		
		attributes.addFlashAttribute("mensagemSuccess", "Evento salvo com sucesso!");
		
		return rv;
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
		
		Evento              evento     = eventoRepository.findById(codigo).get();
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		
		mv.addObject("evento",     evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	@GetMapping(value = "/deletarEvento/{codigo}")
	public RedirectView deletarEvento(@PathVariable("codigo") Long codigo) {
		Evento evento = eventoRepository.findById(codigo).get();
		
		eventoRepository.delete(evento);
		
		return new RedirectView("/listarEventos");
	}
}
