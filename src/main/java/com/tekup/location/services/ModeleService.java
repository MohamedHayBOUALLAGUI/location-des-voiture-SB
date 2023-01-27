package com.tekup.location.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.exception.InvalidOperationException;
import com.tekup.location.entities.Modele;
import com.tekup.location.entities.Voiture;
import com.tekup.location.repository.ModeleRepository;
import com.tekup.location.repository.VoitureRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class ModeleService implements IModeleService {
	
	@Autowired
	private ModeleRepository modeleRepository;
	@Autowired
	private VoitureRepository voitureRepository;
	

	//Récupérer des modèles
	@Override
	public List<Modele> getAllModele(){
		
		return modeleRepository.findAll();
	}

	@Override
	public Modele updateModele(Modele Model) throws EntityNotFoundException {

		if (Model.getId() == null)
		{
			log.error("Modele ID is null");
			return null;
		}
		else if(modeleRepository.findById(Model.getId()) == null) {
			throw new EntityNotFoundException("Aucun modéle avec l'ID = " + Model.getId() + " n' ete trouve dans la BDD");
		}
		else
		{
			return modeleRepository.save(Model);
		}
	}

	//Ajouter un model
	@Override
	public Modele addModel(Modele Model) {

		return modeleRepository.save(Model);

	}
	
	//Récupérer un model par un id
	@Override
	public Optional<Modele> getmodel(Long id) throws EntityNotFoundException {
		
		if (id == null) {
			log.error("Modele ID is null");
			return null;
		}
		else if(modeleRepository.findById(id) == null) {
			throw new EntityNotFoundException("Aucun modéle avec l'ID = " + id + " n' ete trouve dans la BDD");
		}
		else {
			return modeleRepository.findById(id);
		}
		
	}
	
	//Supprimer un model par un id
	@Override
	public void deleteModel(Long id) throws InvalidOperationException {
	    if (id == null) {
	        log.error("Model ID is null");

	      }
	    else if (!voitureRepository.findAllByModeleId(id).isEmpty()) {
	      throw new InvalidOperationException("Impossible de supprimer ce modele qui est deja utilise");
	    }
		else {
			modeleRepository.deleteById(id);
		}
	}

}
