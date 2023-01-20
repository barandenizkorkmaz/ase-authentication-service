package com.ase.authenticationservice.security;

public class SecurityConstants {
    public static final int TOKEN_EXPIRATION = 7200000; // 7200000 milliseconds = 7200 seconds = 2 hours.
    public static final String BEARER = "Bearer "; // Authorization : "Bearer " + Token
    public static final String AUTHORIZATION = "Authorization"; // "Authorization" : Bearer Token
    public static final String REGISTER_PATH = "/user/register"; // Public path that clients can use to register.
    public static final String LOGIN_PATH = "/user/login";
    public static final String KEY_PASSWORD = "aseproject";
    public static final String KEY_FILE = "ase_project.keystore";
}
