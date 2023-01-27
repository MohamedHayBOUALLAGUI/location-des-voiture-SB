package com.tekup.location.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.location.exception.EntityNotFoundException;

import com.tekup.location.entities.Voiture;
import com.tekup.location.repository.VoitureRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VoitureService implements IVoitureService {

	@Autowired
	private VoitureRepository voitureRepository;

	
	//Liste des voitures
		@Override
		public List<Voiture> getAllVoiture(){
			
			return voitureRepository.findAll();
		}
		
		//Ajouter une voiture
		@Override
		public Voiture addVoiture(Voiture voiture) {

			return voitureRepository.save(voiture);


		}
		
		//Récuperer une voiture par un id
		@Override
		public Optional<Voiture> getVoiture(Long id) throws EntityNotFoundException {

			if (id == null)
			{
			      log.error("Voiture ID is null");
			      return null;
			}
			else if(voitureRepository.findById(id) == null)
			{
				throw new EntityNotFoundException("Aucune voiture avec l'ID = " + id + " n' ete trouve dans la BDD");
			}
			else
			{
				return voitureRepository.findById(id);
			}
		}
		
		//Supprimer une voiture par un id
		@Override
		public void deleteVoiture(Long id) {
		    if (id == null) {
		        log.error("Voiture ID is null");

		      }
			else {
				voitureRepository.deleteById(id);
			}
		}

	@Override
	public Voiture updateVoiture(Voiture voiture) throws EntityNotFoundException {

		if (voiture.getId() == null)
		{
			log.error("Voiture ID is null");
			return null;
		}
		else if(voitureRepository.findById(voiture.getId()) == null) {
			throw new EntityNotFoundException("Aucun voiture avec l'ID = " + voiture.getId() + " n' ete trouve dans la BDD");
		}
		else
		{
			return voitureRepository.save(voiture);
		}
	}



	  public List<Voiture> findAllVoitureByModeleId(Long id) {

		return voitureRepository.findAllByModeleId(id);
	  }
	  
	  @Override
	  public void deleteAllVoitureByModeleId(Long id) {
		 List<Voiture>voitures= findAllVoitureByModeleId(id);
		  if (voitures!=null) {
			  for(Voiture v:voitures) {
				  voitureRepository.deleteById(v.getId());
				  
			  }
			  
			  
		  }
		  
		  
	  }

	
	
}
