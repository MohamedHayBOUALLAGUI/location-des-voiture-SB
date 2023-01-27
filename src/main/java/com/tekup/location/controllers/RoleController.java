package com.tekup.location.controllers;

import com.tekup.location.entities.Modele;
import com.tekup.location.entities.Role;
import com.tekup.location.exception.InvalidOperationException;
import com.tekup.location.services.ModeleService;
import com.tekup.location.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;


    //Ajouter un role

    //1- Retourner un forumlaire d'ajout

    @RequestMapping("/addRole")
    public String addRole(Model model) {

        Role role = new Role();
        model.addAttribute("role", role);
        return "new_role";
    }

    //2- Ajout + redirection vers la liste des Roles

    @PostMapping(value="/saveRole")
    public String saveRole(Model model, @ModelAttribute("role") Role role) {
        //modelService.saveModel(model);
        roleService.addRole(role);
        return "redirect:/listeRole";
    }

    //Liste des modeles

    @RequestMapping("/listeRole")
    public String listRole(Model model) {
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("listRoles", listRole);

        return "liste_roles";
    }

	/*@GetMapping("editModel/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Modele modele = modelService.getmodel(id);
        model.addAttribute("model", modele);
        return "update_model";
    }

    @PostMapping("updateModel/{id}")
    public String updateModel(@PathVariable("id") long id,
    		@Valid Modele modele, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            modele.setId(id);
            return "update_model";
        }

        modelService.saveModel(modele);
        model.addAttribute("listModeles", modelService.listModel());
        return "redirect:/listeModel";
    }*/

    @GetMapping("deleteRole/{id}")
    public String deleteRole(@PathVariable("id") long id, Model model) throws InvalidOperationException {

        roleService.deleteRole(id);
        model.addAttribute("listRole", roleService.getAllRole());
        return "redirect:/listeRole";
    }

}
