
package com.project.course.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "subscribes")
@Getter
@Setter
public class Subscribe implements Serializable {
    @Id
    @Column(name="id")
    long id;
    @Column(name="base")
    boolean s1;
    @Column(name="swimming")
    boolean s2;
    @Column(name="trainer")
    boolean s3;
    @OneToMany(mappedBy = "subscribe")
    List <User> users;
}

