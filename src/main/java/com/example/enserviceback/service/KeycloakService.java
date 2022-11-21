package com.example.enserviceback.service;

import com.example.enserviceback.config.Credentials;
import com.example.enserviceback.config.KeycloakAminClientConfig;
import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.entity.Student;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KeycloakService {

    public void addUserToKeycloak(StudentDto student){
        // create a Password Representation that will be accepted by keyCloak from user password
        CredentialRepresentation credential = Credentials.createPasswordCredentials(student.getPassword());

         credential.setTemporary (true); // to set a temporary user password that should be modified in the first login

        // keycloak accept specific information about a user  (object from userRepresentation class )
        UserRepresentation user = new UserRepresentation(); // keycloak class

        user.setUsername(student.getEmail ());
        user.setFirstName(student.getFirstName ());
        user.setLastName(student.getLastName());
        user.setEmail(student.getEmail ());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);

        // with keycloak instance we can create a UserResource  Object then we can perform operations on users in a specific realm
        UsersResource instance = KeycloakAminClientConfig.getInstance ().realm (KeycloakAminClientConfig.realm).users ();
        instance.create(user);

    }
    // delete update activate / deactivate  .... user from keycloak must be here


}
