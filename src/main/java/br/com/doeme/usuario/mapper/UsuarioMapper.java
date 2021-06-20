package br.com.doeme.usuario.mapper;

import br.com.doeme.usuario.dto.UsuarioRequest;
import br.com.doeme.usuario.dto.UsuarioResponse;
import br.com.doeme.usuario.entiry.Perfil;
import br.com.doeme.usuario.entiry.Usuario;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "userType", source = "userType")//expression = "java(usuarioRequest.getUserType().toString())")
    //@Mapping(target = "perfils", source = "perfils") //, qualifiedByName = "map1")
    Usuario from(UsuarioRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    UsuarioResponse to(Usuario usuario);

    List<UsuarioResponse> map(List<Usuario> usuarios);

    //Set<Perfil> map1(Long perfilId);
}