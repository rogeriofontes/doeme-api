package br.com.doeme.user.mapper;

import br.com.doeme.user.dto.UserRequest;
import br.com.doeme.user.dto.UserResponse;
import br.com.doeme.user.entiry.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @InheritConfiguration
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "userType", source = "userType")//expression = "java(userRequest.getUserType().toString())")
    //@Mapping(target = "perfils", source = "perfils") //, qualifiedByName = "map1")
    @Mapping(target = "code", source = "code")
    User from(UserRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    UserResponse to(User user);

    List<UserResponse> map(List<User> users);

    //Set<Perfil> map1(Long perfilId);
}