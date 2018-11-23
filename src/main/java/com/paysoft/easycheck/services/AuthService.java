package com.paysoft.easycheck.services;

import com.paysoft.easycheck.helpers.UserLogin;
import com.paysoft.easycheck.helpers.UserToken;
import com.paysoft.easycheck.mappers.UserMapper;
import com.paysoft.easycheck.models.User;
import com.paysoft.easycheck.repositories.UserRepository;
import com.paysoft.easycheck.utils.PasswordHash;
import com.paysoft.easycheck.utils.TokenGenerator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class AuthService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private TokenGenerator tokenGenerator;

    public boolean logIn(UserLogin userLogin) {
        Map<String, Object> params = new HashMap<>();
        params.put("email", userLogin.getEmail());

        List<User> userList = userRepository.findWithNamedQuery(User.FIND_BY_EMAIL, params, 1);

        if (userList.isEmpty()) {
            return false;
        }

        User user = userList.get(0);

        return new PasswordHash().compare(userLogin.getPassword().toCharArray(), user.getPassword());
    }

    public UserToken generateToken(UserLogin userLogin) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", userLogin.getEmail());

        List<User> userList = userRepository.findWithNamedQuery(User.FIND_BY_EMAIL, params, 1);

        User user = userList.get(0);

        String token = tokenGenerator.generateToken(user);

        return new UserToken().setToken(token).setUser(UserMapper.mapTo(user));
    }
}
