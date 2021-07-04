package br.com.doeme.beneficiary.dto;

import br.com.doeme.beneficiary.model.entity.Beneficiary;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper {

    @InheritConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    Beneficiary from(BeneficiaryRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    BeneficiaryResponse to(Beneficiary beneficiary);

    List<BeneficiaryResponse> map(List<Beneficiary> beneficiaries);
}