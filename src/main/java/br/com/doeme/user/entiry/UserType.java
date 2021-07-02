package br.com.doeme.user.entiry;

import java.util.stream.Stream;

public enum UserType {
    DONOR("DONOR"), //Doador
    GRANTEE("GRANTEE"), //Donatário
    NGO("NGO"); //Ong

    private String type;

    private UserType(String type) {
        this.type = type;
    }

    public static String findUserTypeByType(UserType userType) {
        return Stream.of(UserType.values()).filter(p -> p.type.equals(userType.type)).findAny().orElse(UserType.GRANTEE).type;
    }
}
