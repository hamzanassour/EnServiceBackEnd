package com.example.enserviceback.service;

import com.example.enserviceback.config.Credentials;
import com.example.enserviceback.config.KeycloakAminClientConfig;
import com.example.enserviceback.entity.Student;
import com.example.enserviceback.mapper.StudentMapper;
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
    StudentMapper studentMapper ;

    public void addStudentToKeycloakWithRole(Student student , String roleName){
        // create a Password Representation that will be accepted by keyCloak from user password
        CredentialRepresentation credential = Credentials.createPasswordCredentials(Constants.DEFAULT_PASSWORD);
        credential.setTemporary (true); // to set a temporary user password that should be modified in the first login
        // keycloak accept specific information about a user  (object from userRepresentation class )
        UserRepresentation user = new UserRepresentation();
        user.setUsername(student.getEmail());
        user.setFirstName(student.getFirstName());
        user.setLastName(student.getLastName());
        user.setEmail(student.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        user.setEnabled(true);
        // user.setRealmRoles(Collections.singletonList(roleName));
        // with keycloak instance we can create a UserResource  Object then we can perform operations on users in a specific realm
        UsersResource instance = KeycloakAminClientConfig.getInstance ().realm (KeycloakAminClientConfig.realm).users ();
        Response response = instance.create(user);

        if (response.getStatus() == 201) {
            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            UserResource userResource = instance.get(userId);
            RoleRepresentation roleRepresentation = KeycloakAminClientConfig.getInstance().realm(KeycloakAminClientConfig.realm).roles().get(Constants.STUDENT_ROLE).toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(roleRepresentation));
        } else {
            System.out.println("error");
        }


    }

    // delete update activate / deactivate  .... user from keycloak must be here


}
