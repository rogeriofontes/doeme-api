package br.com.doeme.user.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 7611225270974260535L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String code;
}