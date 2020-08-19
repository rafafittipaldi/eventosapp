package br.com.eventoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventoController {

	@GetMapping("/cadastrarEvento")
	public String index() {
		return "evento/formEvento";
	}
}
