package br.com.doeme.donation.dto;

import br.com.doeme.donation.model.entity.Donation;
import br.com.doeme.donor.model.entity.Donor;
import br.com.doeme.grantee.model.entity.Grantee;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "grantee.id", source = "granteeId")
    @Mapping(target = "donor.id", source = "donorId")
    @Mapping(target = "donation", source = "donation")
    Donation from(DonationRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "granteeId", expression ="java(donation.getGrantee().getId())")
    @Mapping(target = "donorId", expression = "java(donation.getDonor().getId())")
    DonationResponse to(Donation donation);

    List<DonationResponse> map(List<Donation> donations);
}