package com.example.enserviceback.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {



    private String cni ; //  given  by student
    private String cne ;  //  specified by admin
    private long apogee ; //  specified by admin x
    private String level ; // specified by admin
    private String filiere ;  // specified by admin
    private String phone ;    // given  by student
    private LocalDate birthDate ; //  specified by admin

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cni='" + cni + '\'' +
                ", cne='" + cne + '\'' +
                ", apogee=" + apogee +
                ", level='" + level + '\'' +
                ", filiere='" + filiere + '\'' +
                ", phone='" + phone + '\'' +
                ", roles='" + getRoles().toString() + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
