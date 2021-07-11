package br.com.doeme.necessity.dto;

import br.com.doeme.necessity.model.entity.Necessity;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NecessityMapper {

    @InheritConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "local", target = "local")
    @Mapping(source = "necessity", target = "necessity")
    @Mapping(source = "pic", target = "pic")
    @Mapping(source = "beneficiaryId", target = "beneficiary.id")
    Necessity from(NecessityRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(source = "code", target = "code")
    NecessityResponse to(Necessity necessity);

    List<NecessityResponse> map(List<Necessity> necessities);
}