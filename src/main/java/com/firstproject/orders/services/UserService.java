package com.firstproject.orders.services;

import com.firstproject.orders.entities.User;
import com.firstproject.orders.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).get();
    }

    public User insert(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
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
