package br.com.eventoapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public RedirectView incluirConvidado(@Valid Convidado convidado, 
			BindingResult result, 
			RedirectAttributes attributes) {
		
		RedirectView rv = new RedirectView("/detalharEvento/" + convidado.getEvento().getCodigo());
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagemError", "Verifique os campos obrigatórios!");
			return rv;
		}
		
		Evento evento = eventoRepository.findById(convidado.getEvento().getCodigo()).get();
		
		convidado.setEvento(evento);
		
		convidadoRepository.save(convidado);
		
		attributes.addFlashAttribute("mensagemSuccess", "Convidado salvo com sucesso!");
		
		return rv;
	}
	
	@GetMapping(value = "/deletarConvidado/{rg}")
	public RedirectView deletarEvento(@PathVariable("rg") String rg) {
		Convidado convidado = convidadoRepository.findByRg(rg);
		
		convidadoRepository.delete(convidado);
		
		return new RedirectView("/detalharEvento/" + convidado.getEvento().getCodigo());
	}
}
