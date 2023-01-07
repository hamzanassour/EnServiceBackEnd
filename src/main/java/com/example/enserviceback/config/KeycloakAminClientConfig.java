package com.example.enserviceback.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAminClientConfig {

     Keycloak keycloak = null;
    @Value("${KEYCLOAK_SERVER_URL}")
    public  String serverUrl ;
    @Value("${KEYCLOAK_REALM}")
    public  String realm ;
    public  String clientId = "admin-cli";
    @Value("${KEYCLOAK_CLIENT_SECRET}")
    public  String clientSecret ;
    @Value("${KEYCLOAK_ADMIN_USERNAME}")
    public   String userName ;
    @Value("${KEYCLOAK_ADMIN_PASSWORD}")
    public  String password  ;


    public  Keycloak getInstance(){
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
        return keycloak;
    }
}

