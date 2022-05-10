package com.memsource.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemsourceAuthResponse {

    private MemsourceAuthResponseUser user;
    private String token;
    private String expires;
    private String lastInvalidateAllSessionsPerformed;

}
@AllArgsConstructor
@NoArgsConstructor
@Getter
class MemsourceAuthResponseUser {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String role;
    private String id;
    private String uid;

}

