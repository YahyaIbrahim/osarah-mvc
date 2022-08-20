package com.wellware.osara.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wellware.osara.data.objects.Roles;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User extends AuditModel{


    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;
    private boolean isDeaf;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Authorities> authorities = new HashSet<>();


    private Boolean locked = false;
    private Boolean enabled = false;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

}
