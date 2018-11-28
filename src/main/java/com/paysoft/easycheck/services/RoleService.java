package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.RoleDTO;
import com.paysoft.easycheck.mappers.RoleMapper;
import com.paysoft.easycheck.repositories.RoleRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class RoleService {

    @Inject
    RoleRepository roleRepository;

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @return List of {@link RoleDTO}
     */
    public List<RoleDTO> getAll() {
        return RoleMapper.mapTo(roleRepository.findAll());
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param roleDTO {@link RoleDTO}
     * @return {@link RoleDTO}
     */
    public RoleDTO create(RoleDTO roleDTO) {
        return RoleMapper.mapTo(roleRepository.create(RoleMapper.mapTo(roleDTO)));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID role id to find
     * @return Optional of {@link RoleDTO}
     */
    public Optional<RoleDTO> findOne(Long ID) {
        return Optional.ofNullable(RoleMapper.mapTo(roleRepository.find(ID)));
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID role ID
     * @param roleDTO {@link RoleDTO}
     * @return Optional of {@link RoleDTO}
     */
    public Optional<RoleDTO> edit(Long ID, RoleDTO roleDTO) {
        Optional<RoleDTO> role = findOne(ID);

        if (role.isPresent()) {
            return Optional.ofNullable(RoleMapper.mapTo(roleRepository.edit(RoleMapper.mapTo(roleDTO))));
        }

        return Optional.empty();
    }

    /**
     * @author Verem Dugeri <verem.dugeri@gmail.com>
     *
     * @param ID role id to delete
     */
    public void delete(Long ID) {
        roleRepository.delete(ID);
    }
}
