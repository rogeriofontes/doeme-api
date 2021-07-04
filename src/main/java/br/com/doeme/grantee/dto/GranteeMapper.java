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
    @Mapping(source = "id", target = "id")
    @Mapping(source = "local", target = "local")
    @Mapping(source = "necessity", target = "necessity")
    @Mapping(source = "pic", target = "pic")
    @Mapping(source = "beneficiaryId", target = "beneficiary.id")
    Grantee from(GranteeRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    GranteeResponse to(Grantee user);

    List<GranteeResponse> map(List<Grantee> fornecedores);
}