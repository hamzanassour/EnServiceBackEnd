package com.example.enserviceback.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAminClientConfig {

    static Keycloak keycloak = null;
    //@Value("${KEYCLOAK_SERVER_URL}")
    public  static String serverUrl = "http://localhost:8080/auth";
    //@Value("${KEYCLOAK_REALM}")
    public  static String realm = "test";
    public final static String clientId = "admin-cli";
    //@Value("${KEYCLOAK_CLIENT_SECRET}")
    public  static String clientSecret = "d3lbAwUz7Lk5dlFluBTu51q1Ac2mvG0s";
    //@Value("${KEYCLOAK_ADMIN_USERNAME}")
    public  static String userName = "admin" ;
    //@Value("${KEYCLOAK_ADMIN_PASSWORD}")
    public  static String password = "admin" ;


    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder ()
                            .connectionPoolSize(10)
                            .build()).build ();
        }
        return keycloak;
    }
}

