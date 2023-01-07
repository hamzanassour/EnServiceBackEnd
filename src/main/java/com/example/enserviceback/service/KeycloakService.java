package com.example.enserviceback.service;

import com.example.enserviceback.config.Credentials;
import com.example.enserviceback.config.KeycloakAminClientConfig;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.entity.User;
import com.example.enserviceback.exceptions.SavingStudentInProviderException;
import com.example.enserviceback.utils.Constants;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class KeycloakService {


    @Autowired
    KeycloakAminClientConfig keycloakAminClientConfig ;


    public void addUserToKeycloakWithRole(User userToBeSaved , String roleName) throws SavingStudentInProviderException {
        CredentialRepresentation credential ;
        if (checkIfUserIsStudent(userToBeSaved)){
            credential   = Credentials.createPasswordCredentials(Constants.STUDENT_DEFAULT_PASSWORD);
        }
        else {
            credential = Credentials.createPasswordCredentials(Constants.TEACHER_DEFAULT_PASSWORD);
        }
        credential.setTemporary (true);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userToBeSaved.getEmail());
        user.setFirstName(userToBeSaved.getFirstName());
        user.setLastName(userToBeSaved.getLastName());
        user.setEmail(userToBeSaved.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource instance = keycloakAminClientConfig.getInstance ().realm (keycloakAminClientConfig.realm).users ();
        Response response = instance.create(user);

        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            UserResource userResource = instance.get(userId);
            RoleRepresentation roleRepresentation = keycloakAminClientConfig.getInstance().realm(keycloakAminClientConfig.realm).roles().get(roleName).toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
        } else {

           throw  new SavingStudentInProviderException("error happened when we are trying to save the user please verify that the user not already exist ");
        }


    }

    private boolean checkIfUserIsStudent(User user){
        if(user instanceof Student){
            return true;
        }
        else return false ;
    }



}
