package com.tekup.location.controllers;

import java.util.List;

//import com.tekup.location.entities.Voiture;
import com.tekup.location.exception.InvalidOperationException;
//import com.tekup.location.services.IVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tekup.location.entities.Modele;
import com.tekup.location.services.ModeleService;
import com.tekup.location.services.VoitureService;

@Controller
public class ModeleController {
	
	
	@Autowired
	private ModeleService modelService;
	@Autowired
	private VoitureService voitureService;

	//Ajouter un model 
	
	//1- Retourner un forumlaire d'ajout
	
	@RequestMapping("/admin/addModel")
	public String addModel(Model model) {
		
		Modele modele = new Modele();
		model.addAttribute("modele", modele);
		return "new_modele";
	}
	
	//2- Ajout + redirection vers la liste des modeles
	
	@PostMapping(value="/admin/saveModel")
	public String saveModel(Model model, @ModelAttribute("modele") Modele modele) {
		//modelService.saveModel(model);
		modelService.addModel(modele);
		return "redirect:/listeModel";
	}
	
	//Liste des modeles
	
	@RequestMapping("/admin/listeModel")
	public String listModel(Model model) {
	    List<Modele> listModel = modelService.getAllModele();
	    model.addAttribute("listModeles", listModel);
	     
	    return "liste_modeles";
	}
	//Liste des modeles user
	
	@RequestMapping("/user/listeModel")
	public String listModels(Model model) {
	    List<Modele> listModel = modelService.getAllModele();
	    model.addAttribute("listModeles", listModel);
	     
	    return "liste_modeles_user";
	}


    @GetMapping("/admin/deleteModel/{id}")
    public String deleteModel(@PathVariable("id") long id, Model model) throws InvalidOperationException {
    	voitureService.deleteAllVoitureByModeleId(id);
    modelService.deleteModel(id);
        model.addAttribute("listeModel", modelService.getAllModele());
        return "redirect:/admin/listeModel";
    }

}
