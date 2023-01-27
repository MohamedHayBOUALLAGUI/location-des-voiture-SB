package com.tekup.location.controllers;

import com.tekup.location.entities.Location;
import com.tekup.location.entities.Voiture;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.services.ILocationService;
import com.tekup.location.services.IVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {

    @Autowired
    private ILocationService locationService;
    @Autowired
    private IVoitureService voitureService;

    @RequestMapping("/locations")
    public String listLocations(Model model) {
        List<Location> locations = locationService.getAllLocation();

        locations.forEach(l -> l.calculatePrix());
        model.addAttribute("listLocations", locations);


        return "liste_location";
    }

    @GetMapping("/addLocation/{id}")
    public String showaddLocation(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {

        Optional<Voiture> voiture = voitureService.getVoiture(id);
        Voiture v = voiture.get();

        Location location = new Location();

        model.addAttribute("LocationForm", location);
        model.addAttribute("Voiture",v);

        return "new_location";
    }

    @GetMapping("/addLocation/")
    public String showaddLocation(Model model) {

        Location location = new Location();

        model.addAttribute("LocationForm", location);


        return "update_location";
    }

    @RequestMapping(value="/addLocation", method= RequestMethod.POST)
    public String saveLocation(Model model,@ModelAttribute("LocationForm") Location location,
                               @ModelAttribute("Voiture") Voiture voiture)  {

        //location.setVoiture(voiture);
        /*v.setId(voitureForm.getId());
        v.setImmatriculation(voitureForm.getImmatriculation());
        v.setDateMiseEnCirculation(voitureForm.getDateMiseEnCirculation());
        v.setModel(voitureForm.getModel());
        v.setImagePath(voitureForm.getImagePath());
        v.setPrixJour(voitureForm.getPrixJour());*/

        locationService.addLocation(location);

        return "redirect:/locations";
    }

    @GetMapping("updateLocation/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
        Optional<Location> location = locationService.getLocation(id);
        Location L = location.get();
        if (L != null) {
            model.addAttribute("Location", L);

        }
        return "update_location";
    }

    @PostMapping("updateLocation/{id}")
    public String updateLocation(@PathVariable("id") Long id, @ModelAttribute("locations") Location locationForm, Model model
    ) throws Exception {
        Optional<Location> location = locationService.getLocation(id);
        Location L = location.get();
        if (L != null) {
            locationService.updateLocation(locationForm);

        }
        model.addAttribute("listlocation", locationService.getAllLocation());
        return "redirect:/locations";
    }

    @RequestMapping(value = { "/deleteLocation/{id}" }, method = RequestMethod.GET)
    public String deleteLocation(Model model, @PathVariable("id") Long id) throws EntityNotFoundException {

        Optional<Location> location = locationService.getLocation(id);
        Location L = location.get();

        if (L != null) {
            locationService.deleteLocation(id);

        }

        return "redirect:/locations";
    }



}
