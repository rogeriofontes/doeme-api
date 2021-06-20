package br.com.doeme.fornecedor.dto;

import br.com.doeme.fornecedor.model.entity.Fornecedor;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    Fornecedor from(FornecedorRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    FornecedorResponse to(Fornecedor usuario);

    List<FornecedorResponse> map(List<Fornecedor> fornecedores);
}