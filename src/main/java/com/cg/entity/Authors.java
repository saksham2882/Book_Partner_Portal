package com.cg.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authors {
    @Id
    @Column(name = "au_id",length = 11)
    private String auId;

    @Column(name = "au_lname", length = 40,nullable = false)
    private String LastName;

    @Column(name = "au_fname", length = 20, nullable = false)
    private String FirstName;

    @Column(name = "phone", length = 12, nullable = false)
    private String phone;

    @Column(name = "address", length = 40)
    private String address;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "state" , length = 2)
    private String state;

    @Column(name = "zip", length = 5)
    private String zip;

    @Column(name = "contract", nullable = false)
    private String contract;

    @OneToMany(MappedBy = "authors",cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<TitleAuthor> titleAuthors;

}
