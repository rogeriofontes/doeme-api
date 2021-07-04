package br.com.doeme.ong.dto;

import br.com.doeme.ong.model.entity.Ong;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OngMapper {

    @InheritConfiguration
    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    Ong from(OngRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    OngResponse to(Ong ong);

    List<OngResponse> map(List<Ong> ongs);
}