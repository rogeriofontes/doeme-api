package br.com.doeme.donationapprove.dto;

import br.com.doeme.donationapprove.model.entity.DonationApprove;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonationApproveMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "donation.id", source = "donationId")
    @Mapping(target = "ngo.id", source = "ngoId")
    @Mapping(target = "approved", source = "approved")
    DonationApprove from(DonationApproveRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "donationId", expression ="java(donationApprove.getDonation().getId())")
    @Mapping(target = "ngoId", expression = "java(donationApprove.getNgo().getId())")
    DonationApproveResponse to(DonationApprove donationApprove);

    List<DonationApproveResponse> map(List<DonationApprove> donationApproves);
}