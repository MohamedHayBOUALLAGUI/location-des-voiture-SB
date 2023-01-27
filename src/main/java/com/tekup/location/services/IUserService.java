package com.tekup.location.services;

import com.tekup.location.entities.*;
import com.tekup.location.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public Optional<User> getUser(Long id) throws EntityNotFoundException;
    public User addUser(User user);
    public User updateUser(User user) throws EntityNotFoundException;
    public void deleteUser(Long id);
    public List<User> getAllUser();
    public List<User> findAllUserByRole(Long id);

}
