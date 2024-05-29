package org.nstut.luvit.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Integer age;
    private String gender;
    private String preference;
}
