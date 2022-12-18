package com.peaksoft.project_on_restapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    @ManyToMany(targetEntity = User.class, mappedBy = "roles", cascade = {DETACH, REFRESH, MERGE})
    private List<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
