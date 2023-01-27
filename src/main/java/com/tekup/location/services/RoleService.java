package com.tekup.location.services;

import com.tekup.location.entities.Role;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.exception.InvalidOperationException;
import com.tekup.location.repository.RoleRepository;
import com.tekup.location.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class RoleService implements IRoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role R) throws EntityNotFoundException {
        if (R.getId() == null)
        {
            log.error("Role ID is null");
            return null;
        }
        else if(roleRepository.findById(R.getId()) == null) {
            throw new EntityNotFoundException("Aucun role avec l'ID = " + R.getId() + " n' ete trouve dans la BDD");
        }
        else
        {
            return roleRepository.save(R);
        }
    }

    @Override
    public Role addRole(Role R) {
        return roleRepository.save(R);

    }

    @Override
    public Optional<Role> getRole(Long id) throws EntityNotFoundException {
        if (id == null) {
            log.error("Role ID is null");
            return null;
        }
        else if(roleRepository.findById(id) == null) {
            throw new EntityNotFoundException("Aucun role avec l'ID = " + id + " n' ete trouve dans la BDD");
        }
        else {
            return roleRepository.findById(id);
        }
    }

    @Override
    public void deleteRole(Long id) throws InvalidOperationException {

        if (id == null) {
            log.error("Role ID is null");

        }
        else if (!userRepository.findAllByRoleId(id).isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer ce role qui est deja utilise");
        }
        else {
            roleRepository.deleteById(id);
        }
    }

    }

