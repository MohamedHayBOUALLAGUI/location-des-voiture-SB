package com.tekup.location.services;

import com.tekup.location.entities.Role;
import com.tekup.location.entities.User;
import com.tekup.location.exception.EntityNotFoundException;
import com.tekup.location.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> getUser(Long id) throws EntityNotFoundException {
        if (id == null) {
            log.error("User ID is null");
            return null;
        }
        else if(userRepository.findById(id) == null) {
            throw new EntityNotFoundException("Aucun utilisateur avec l'ID = " + id + " n' ete trouve dans la BDD");
        }
        else {
            return userRepository.findById(id);
        }
    }

    @Override
    public User addUser(User user) {

            return userRepository.save(user);

    }

    @Override
    public User updateUser(User user) throws EntityNotFoundException {
        if (user.getId() == null)
        {
            log.error("User ID is null");
            return null;
        }
        else if(userRepository.findById(user.getId()) == null) {
            throw new EntityNotFoundException("Aucun utilisateur avec l'ID = " + user.getId() + " n' ete trouve dans la BDD");
        }
        else
        {
            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (id == null) {
            log.error("User ID is null");
        }
        else {
            userRepository.deleteById(id);
        }

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllUserByRole(Long id) {

        return userRepository.findAllByRoleId((id));
    }

}
