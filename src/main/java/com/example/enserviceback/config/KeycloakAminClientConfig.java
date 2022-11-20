package com.example.enserviceback.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAminClientConfig {

    static Keycloak keycloak = null;
    public final static String serverUrl = "http://localhost:8080/auth";
    public final static String realm = "test";
    public final static String clientId = "admin-cli";
    public final static String clientSecret = "h2fh2PlPxfn1DV0UGfFVkEVAyBYXhcaO";
    public final static String userName = "admin";
    public final static String password = "admin";


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

