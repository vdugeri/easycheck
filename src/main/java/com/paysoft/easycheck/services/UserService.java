package com.paysoft.easycheck.services;

import com.paysoft.easycheck.utils.PaginatedResource;
import com.paysoft.easycheck.dtos.UserDTO;
import com.paysoft.easycheck.mappers.UserMapper;
import com.paysoft.easycheck.models.User;
import com.paysoft.easycheck.repositories.UserRepository;
import com.paysoft.easycheck.utils.PaginationMetadata;
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
    public PaginatedResource<UserDTO> findAll(Integer limit, Integer offset) {
        int total = userRepository.count();
        List<User> users = userRepository.findWithLimitAndOffset(limit, offset);

        int pages = (int) Math.ceil(total / limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setPages(pages);
        metadata.setPerPage(limit);
        metadata.setTotal(total);

        PaginatedResource<UserDTO> paginatedUsers = new PaginatedResource<>();
        paginatedUsers.setMeta(metadata);
        paginatedUsers.setData(UserMapper.mapTo(users));


        return paginatedUsers;
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param user new user
     *
     * @return the created user
     */
    public User createUser(UserDTO user) {
        user.setPassword(new PasswordHash().hash(user.getPassword().toCharArray()));
        return userRepository.create(UserMapper.mapTo(user));
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
    public User update(UserDTO user) {
        return userRepository.edit(UserMapper.mapTo(user));
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
