package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.AdminDTO;
import com.paysoft.easycheck.mappers.AdminMapper;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.models.Role;
import com.paysoft.easycheck.repositories.AdminRepository;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class AdminService {

    @Inject
    AdminRepository adminRepository;


    public List<AdminDTO> allAdmins() {
        return AdminMapper.mapTo(adminRepository.findAll());
    }

    public AdminDTO save(AdminDTO adminDTO, Merchant merchant, List<Role> roles) {
        return AdminMapper.mapTo(adminRepository.create(AdminMapper.mapTo(adminDTO, merchant, roles)));
    }

    public Optional<AdminDTO> find(Long ID) {
        return Optional.ofNullable(AdminMapper.mapTo(adminRepository.find(ID)));
    }

    public AdminDTO update(AdminDTO adminDTO, Merchant merchant, List<Role> roles) {
        return AdminMapper.mapTo(adminRepository.edit(AdminMapper.mapTo(adminDTO, merchant, roles)));
    }

    public void delete(Long ID) {
        adminRepository.delete(ID);
    }
}
