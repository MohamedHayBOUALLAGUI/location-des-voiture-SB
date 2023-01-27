package com.tekup.location.services;

import com.tekup.location.entities.Voiture;
import com.tekup.location.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IVoitureService {

    public List<Voiture> getAllVoiture();
    public Voiture addVoiture(Voiture voiture);
    public Optional<Voiture> getVoiture(Long id) throws EntityNotFoundException;
    public void deleteVoiture(Long id);
    public Voiture updateVoiture(Voiture voiture) throws EntityNotFoundException;
    public List<Voiture> findAllVoitureByModeleId(Long modele);
    public void deleteAllVoitureByModeleId(Long id);

}
