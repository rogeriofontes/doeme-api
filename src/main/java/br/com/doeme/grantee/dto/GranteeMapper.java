package br.com.doeme.grantee.dto;

import br.com.doeme.grantee.model.entity.Grantee;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GranteeMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "local", source = "local")
    @Mapping(target = "necessity", source = "necessity")
    @Mapping(target = "pic", source = "pic")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "code", source = "code")
    Grantee from(GranteeRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    GranteeResponse to(Grantee usuario);

    List<GranteeResponse> map(List<Grantee> fornecedores);
}