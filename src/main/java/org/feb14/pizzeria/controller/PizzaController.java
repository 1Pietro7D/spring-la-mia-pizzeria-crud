package org.feb14.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.feb14.pizzeria.model.Pizza;
import org.feb14.pizzeria.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	// iniettiamo automaticamente
	private @Autowired PizzaRepository pizzaRepository;

	@GetMapping
	public String index(Model modList) {
		List<Pizza> pizzaList = pizzaRepository.findAll(); // restituisce un elenco di istanze libro
		modList.addAttribute("pizze", pizzaList);
		return "pizze/list";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") String id, Model modShow) {
		try {
			Integer validId = Integer.parseInt(id);
		try {
		Optional<Pizza> pizza = pizzaRepository.findById(validId); // restituisce un'istanza Optional con dentro forse una
																// pizza
					modShow.addAttribute("pizza", pizza.get());
			return "pizze/detail";
		} catch (Exception e) {
			return "/errorPage";
		}
		}catch (NumberFormatException e) {
			return "/errorPage";
		}
	}
}
