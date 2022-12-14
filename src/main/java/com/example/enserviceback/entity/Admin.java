package com.example.enserviceback.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Admin extends User{

    private  String cni ;


    @OneToMany(mappedBy = "admin")
    private List<Announcement> announcements;

}
