package com.paysoft.easycheck.mappers;

import com.paysoft.easycheck.dtos.AdminDTO;
import com.paysoft.easycheck.dtos.MerchantDTO;
import com.paysoft.easycheck.models.Admin;
import com.paysoft.easycheck.models.Merchant;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MerchantMapper {

    public static Merchant mapTo(MerchantDTO merchantDTO, List<Admin> admins) {
        if (merchantDTO == null) {
            return null;
        }

        Merchant merchant = new Merchant();
        merchant.setAddress(merchantDTO.getAddress());
        merchant.setID(merchantDTO.getID());
        merchant.setName(merchantDTO.getName());
        merchant.setAdmins(admins);

        return merchant;
    }


    public static MerchantDTO mapTo(Merchant merchant) {
        if (merchant == null) {
            return null;
        }

        return new MerchantDTO(merchant.getID(), merchant.getName(), merchant.getAddress(), merchant.isVerified());
    }


    public static List<MerchantDTO> mapTo(List<Merchant> merchants) {
        if (merchants.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return merchants
            .stream()
            .map(merchant -> mapTo(merchant))
            .filter(merchantDTO -> merchantDTO != null)
            .collect(Collectors.toList());
    }
}
