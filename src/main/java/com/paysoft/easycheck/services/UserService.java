package com.paysoft.easycheck.services;

import com.paysoft.easycheck.models.User;
import com.paysoft.easycheck.repositories.UserRepository;
import com.paysoft.easycheck.utils.PasswordHash;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        user.setPassword(new PasswordHash().hash(user.getPassword().toCharArray()));
        return userRepository.create(user);
    }

    public Optional<User> findOne(Long ID) {
        return Optional.ofNullable(userRepository.find(ID));
    }

    public User update(User user) {
        return userRepository.edit(user);
    }

    public void delete(Long ID) {
        userRepository.delete(ID);
    }
}
