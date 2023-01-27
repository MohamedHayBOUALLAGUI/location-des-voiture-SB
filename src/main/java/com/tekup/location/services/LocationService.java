package com.tekup.location.services;

import com.tekup.location.entities.Location;
import com.tekup.location.entities.User;
import com.tekup.location.entities.Voiture;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class LocationService implements ILocationService{

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    @Override
    public Location addLocation(Location location) {

            return locationRepository.save(location);

    }

    @Override
    public Optional<Location> getLocation(Long id) throws EntityNotFoundException {
        if (id == null)
        {
            log.error("Location ID is null");
            return null;
        }
        else if(locationRepository.findById(id) == null)
        {
            throw new EntityNotFoundException("Aucune location avec l'ID = " + id + " n' ete trouve dans la BDD");
        }
        else
        {
            return locationRepository.findById(id);
        }
    }

    @Override
    public void deleteLocation(Long id) {

        if (id == null) {
            log.error("Location ID is null");

        }
        else {
            locationRepository.deleteById(id);
        }

    }

    @Override
    public void deleteLocationByVoitureId(Voiture voiture) {

        if (voiture.getId() == null) {
            log.error("Client ID is null");

        }
        else {
            locationRepository.deleteById(voiture.getId());
        }

    }

    @Override
    public Location updateLocation(Location location) throws EntityNotFoundException {
        if (location.getId() == null)
        {
            log.error("Location ID is null");
            return null;
        }
        else if(locationRepository.findById(location.getId()) == null) {
            throw new EntityNotFoundException("Aucun location avec l'ID = " + location.getId() + " n' ete trouve dans la BDD");
        }
        else
        {
            return locationRepository.save(location);
        }
    }

    @Override
    public List<Location> findAllLocationByUserId(User user) {
        return locationRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Location> findAllLocationByVoitureId(Voiture voiture) {
        return locationRepository.findAllByVoitureId(voiture.getId());
    }
}
