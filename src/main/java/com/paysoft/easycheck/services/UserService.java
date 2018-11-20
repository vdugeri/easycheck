package com.paysoft.easycheck.services;

import com.paysoft.easycheck.models.User;
import com.paysoft.easycheck.repositories.UserRepository;
import com.paysoft.easycheck.utils.PasswordHash;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Verem Dugeri <verem.dugeri@gmail.com>
 */
@Stateless
@LocalBean
public class UserService {

    @Inject
    private UserRepository userRepository;

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @return List of users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param user new user
     *
     * @return the created user
     */
    public User createUser(User user) {
        user.setPassword(new PasswordHash().hash(user.getPassword().toCharArray()));
        return userRepository.create(user);
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID Long
     *
     * @return Optional of User
     */
    public Optional<User> findOne(Long ID) {
        return Optional.ofNullable(userRepository.find(ID));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param user User
     * @return found User
     */
    public User update(User user) {
        return userRepository.edit(user);
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID Long
     */
    public void delete(Long ID) {
        userRepository.delete(ID);
    }
}
