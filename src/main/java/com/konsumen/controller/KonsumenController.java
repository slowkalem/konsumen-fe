package com.konsumen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.konsumen.model.Konsumen;
import com.konsumen.service.KonsumenService;

@Controller
public class KonsumenController {
	private KonsumenService konsumenService;

	public KonsumenController(KonsumenService konsumenService) {
		this.konsumenService = konsumenService;
	}

	@GetMapping("/konsumen")
	public String getKonsumen(Model model) {
		try {
			List<Konsumen> konsumen = konsumenService.getKonsumen();
			model.addAttribute("konsumen", konsumen);
			return "konsumen";
		} catch (Exception e) {
			System.out.print(e);
			return "konsumen";
		}
	}

	@GetMapping("/konsumen/add")
	public String addKonsumen(Model model) {
		Konsumen konsumen = new Konsumen();
		model.addAttribute("konsumen", konsumen);
		return "add-konsumen";
	}

	@PostMapping("/save")
	public String saveKonsumen(@ModelAttribute("konsumen") Konsumen konsumen) {
		String response = konsumenService.saveKonsumen(konsumen);
		System.out.print(response);
		return "redirect:/konsumen";
	}

	@GetMapping("/konsumen/update/{id}")
	public String updateKonsumenView(@PathVariable("id") int id, Model model) {
		Konsumen konsumen = konsumenService.getKonsumenById(id);
		model.addAttribute("konsumen", konsumen);
		return "update-konsumen";
	}

	@PostMapping("/update")
	public String updateKonsumen(@ModelAttribute("konsumen") Konsumen konsumen) {
		ResponseEntity<String> response = konsumenService.updateKonsumen(konsumen);
		System.out.print(response.getBody());
		return "redirect:/konsumen";
	}

	@GetMapping("/delete/{id}")
	public String DeleteKonsumen(@PathVariable("id") int id) {
		ResponseEntity<String> response = konsumenService.deleteKonsumen(id);
		System.out.print(response.getBody());
		return "redirect:/konsumen";
	}
}
