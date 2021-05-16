package com.project.course.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    long id;
    @Column(name="name")
    @Size(min=2, max=30)
    String name;
    @Column(name= "final")
    String finalDate;
    @Column(name="login")
    @Email
    String login;
    @Column(name="password")
    @NotNull
    String password;
    @Column(name="card")
 //   @Min(0)
    int cardNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="subscribe_id")
    Subscribe subscribe;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;

}
