package com.tekup.location.services;

import com.tekup.location.entities.Location;
import com.tekup.location.entities.User;
import com.tekup.location.entities.Voiture;
import com.tekup.location.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ILocationService {

    public List<Location> getAllLocation();
    public Location addLocation(Location location);
    public Optional<Location> getLocation(Long id) throws EntityNotFoundException;
    public void deleteLocation(Long id);
    public void deleteLocationByVoitureId(Voiture voiture);
    public Location updateLocation(Location location) throws EntityNotFoundException;
    public List<Location> findAllLocationByUserId(User user);
    public List<Location> findAllLocationByVoitureId(Voiture voiture);
}
