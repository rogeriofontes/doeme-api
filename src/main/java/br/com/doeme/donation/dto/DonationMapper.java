package br.com.doeme.donation.dto;

import br.com.doeme.donation.model.entity.Donation;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "necessity.id", source = "necessityId")
    @Mapping(target = "donor.id", source = "donorId")
    @Mapping(target = "ngo.id", source = "ngoId")
    @Mapping(target = "donation", source = "donation")
    @Mapping(target = "code", source = "code")
    Donation from(DonationRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "necessityId", expression ="java(donation.getNecessity().getId())")
    @Mapping(target = "donorId", expression = "java(donation.getDonor().getId())")
    @Mapping(target = "ngoId", expression = "java(donation.getNgo().getId())")
    DonationResponse to(Donation donation);

    List<DonationResponse> map(List<Donation> donations);
}