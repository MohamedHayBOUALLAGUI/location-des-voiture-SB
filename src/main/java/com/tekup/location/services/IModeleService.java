package com.tekup.location.services;

import com.tekup.location.entities.Modele;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.exception.InvalidOperationException;

import java.util.List;
import java.util.Optional;

public interface IModeleService {

    public List<Modele> getAllModele();
    public Modele updateModele(Modele M) throws EntityNotFoundException;
    public Modele addModel(Modele model);
    public Optional<Modele> getmodel(Long id) throws EntityNotFoundException;
    public void deleteModel(Long id) throws InvalidOperationException;
}
