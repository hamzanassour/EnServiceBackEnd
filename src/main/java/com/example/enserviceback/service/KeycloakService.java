package com.example.enserviceback.service;

import com.example.enserviceback.config.Credentials;
import com.example.enserviceback.config.KeycloakAminClientConfig;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.exceptions.SavingStudentInProviderException;
import com.example.enserviceback.utils.Constants;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class KeycloakService {


    public void addStudentToKeycloakWithRole(Student student , String roleName) throws SavingStudentInProviderException {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(Constants.DEFAULT_PASSWORD);
        credential.setTemporary (true);
        UserRepresentation user = new UserRepresentation();
        user.setUsername(student.getEmail());
        user.setFirstName(student.getFirstName());
        user.setLastName(student.getLastName());
        user.setEmail(student.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        UsersResource instance = KeycloakAminClientConfig.getInstance ().realm (KeycloakAminClientConfig.realm).users ();
        Response response = instance.create(user);

        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            UserResource userResource = instance.get(userId);
            RoleRepresentation roleRepresentation = KeycloakAminClientConfig.getInstance().realm(KeycloakAminClientConfig.realm).roles().get(Constants.STUDENT_ROLE).toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
        } else {

           throw  new SavingStudentInProviderException("error happened when we are trying to save the user please verify that the user not already exist ");
        }


    }


}
