package com.tekup.location.controllers;

import com.tekup.location.entities.Modele;
import com.tekup.location.entities.Role;
import com.tekup.location.entities.User;
import com.tekup.location.entities.Voiture;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.services.IRoleService;
import com.tekup.location.services.IUserService;
import com.tekup.location.services.IVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/users")
    public String listUsers(Model model) {
        List<User> Users = userService.getAllUser();

        model.addAttribute("listUsers", Users);

        return "liste_users";
    }
    @GetMapping("/addUser")
    public String showaddUser(Model model) {

        User user = new User();
        List<Role> listRole = roleService.getAllRole();
        model.addAttribute("roleList", listRole);

        model.addAttribute("UserForm", user);


        return "new_user";
    }

    @RequestMapping(value="/addUser", method= RequestMethod.POST)
    public String saveUser(Model model,@ModelAttribute("UserForm") User user) {
        userService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping("updateUser/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<User> user = userService.getUser(id);
        User u = user.get();

        if (u != null) {
            model.addAttribute("user", u);

        }
        return "update_user";
    }

    @PostMapping("updateUser/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("users") User userForm, Model model
    ) throws Exception {
        Optional<User> user = userService.getUser(id);
        User u = user.get();

        u.setId(userForm.getId());
        u.setUsername(userForm.getUsername());
        u.setCin(userForm.getCin());
        u.setNom(userForm.getNom());
        u.setPrenom(userForm.getPrenom());
        u.setAdresse(userForm.getAdresse());
        u.setPassword(userForm.getPassword());

        userService.updateUser(u);


        model.addAttribute("listUsers", userService.getAllUser());
        return "redirect:/users";
    }

    @RequestMapping("/userRole/{id}")
    public String listUserRole(@PathVariable("id") Long id,Model model) throws EntityNotFoundException {

        List<User> Users = userService.findAllUserByRole(id);

        model.addAttribute("listUserRole", Users);

        return "liste_users_Role";
    }


    @RequestMapping(value = { "/deleteUser/{id}" }, method = RequestMethod.GET)
    public String deleteUser(Model model, @PathVariable("id") Long id) throws EntityNotFoundException {

        Optional<User> user = userService.getUser(id);
        User u = user.get();
        if (u != null) {
            userService.deleteUser(id);

        }

        return "redirect:/users";
    }
  @GetMapping({"/", "/welcome"})
  public String welcome(Model model) {
      return "home";
  }



}
