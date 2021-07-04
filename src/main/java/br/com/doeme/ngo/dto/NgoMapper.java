package br.com.doeme.ngo.dto;

import br.com.doeme.ngo.model.entity.Ngo;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NgoMapper {

    @InheritConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    Ngo from(NgoRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    NgoResponse to(Ngo ngo);

    List<NgoResponse> map(List<Ngo> ngos);
}