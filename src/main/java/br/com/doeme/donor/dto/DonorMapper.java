package br.com.doeme.donor.dto;

import br.com.doeme.donor.model.entity.Donor;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonorMapper {

    @InheritConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "local", target = "local")
    @Mapping(source = "userId", target = "user.id")
    Donor from(DonorRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    DonorResponse to(Donor donor);

    List<DonorResponse> map(List<Donor> donors);
}