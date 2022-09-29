package com.firstproject.orders.services;

import com.firstproject.orders.entities.User;
import com.firstproject.orders.repositories.UserRepository;
import com.firstproject.orders.services.exceptions.DatabaseException;
import com.firstproject.orders.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User userUpdated) {
        User currentUser = repository.getReferenceById(id);
        updateData(currentUser, userUpdated);
        return repository.save(currentUser);
    }

    public void updateData(User oldUser, User newUser) {
        if (Objects.nonNull(newUser.getName())) {
            oldUser.setName(newUser.getName());
        }

        if (Objects.nonNull(newUser.getEmail())) {
            oldUser.setEmail(newUser.getEmail());
        }

        if (Objects.nonNull(newUser.getPhone())) {
            oldUser.setPhone(newUser.getPhone());
        }

        if (Objects.nonNull(newUser.getPassword())) {
            oldUser.setPassword(newUser.getPassword());
        }
    }
}
