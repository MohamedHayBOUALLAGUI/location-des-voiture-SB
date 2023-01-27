package com.tekup.location.services;

import com.tekup.location.entities.Modele;
import com.tekup.location.entities.Role;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.exception.InvalidOperationException;

import java.util.List;
import java.util.Optional;

public interface IRoleService {


    public List<Role> getAllRole();
    public Role updateRole(Role R) throws EntityNotFoundException;
    public Role addRole(Role R);
    public Optional<Role> getRole(Long id) throws EntityNotFoundException;
    public void deleteRole(Long id) throws InvalidOperationException;
}
