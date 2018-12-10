package com.paysoft.easycheck.services;

import com.paysoft.easycheck.dtos.MerchantDTO;
import com.paysoft.easycheck.mappers.MerchantMapper;
import com.paysoft.easycheck.models.Admin;
import com.paysoft.easycheck.models.Merchant;
import com.paysoft.easycheck.repositories.MerchantRepository;
import com.paysoft.easycheck.utils.PaginatedResource;
import com.paysoft.easycheck.utils.PaginationMetadata;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
@LocalBean
public class MerchantService {

    @Inject
    MerchantRepository merchantRepository;

    public PaginatedResource<MerchantDTO> allMerchants(int limit, int offset) {
        int total = merchantRepository.count();

        List<Merchant> merchants = merchantRepository.findWithLimitAndOffset(limit, offset);

        int pages = (int) Math.ceil(total / limit) + 1;
        int currPage = (int) Math.floor(offset / limit) + 1;

        PaginationMetadata metadata = new PaginationMetadata();
        metadata.setCurrPage(currPage);
        metadata.setPages(pages);
        metadata.setPerPage(limit);
        metadata.setTotal(total);

        PaginatedResource<MerchantDTO> paginatedMerchants = new PaginatedResource<>();
        paginatedMerchants.setMeta(metadata);
        paginatedMerchants.setData(MerchantMapper.mapTo(merchants));

        return paginatedMerchants;
    }

    public MerchantDTO save(MerchantDTO merchantDTO, List<Admin> admins) {
        return MerchantMapper.mapTo(merchantRepository.create(MerchantMapper.mapTo(merchantDTO, admins)));
    }

    public Optional<MerchantDTO> find(Long ID) {
        return Optional.ofNullable(MerchantMapper.mapTo(merchantRepository.find(ID)));
    }

    public Optional<MerchantDTO> udpate(Long ID, MerchantDTO merchantDTO, List<Admin> admins) {
        Merchant merchant = merchantRepository.find(ID);

        if (merchant == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(MerchantMapper.mapTo(merchantRepository.edit(MerchantMapper.mapTo(merchantDTO, admins))));
    }

    public void delete(Long ID) {
        merchantRepository.delete(ID);
    }
}
