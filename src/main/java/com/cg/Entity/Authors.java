package com.cg.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;

@Entity
@Table(name="authors")
public class Authors {
    @Id
    @Column(name = "au_id", length = 11, nullable = false)
    private String auId;

    @Column(name = "au_lname", length = 40, nullable = false)
    private String auLname;

    @Column(name = "au_fname", length = 20, nullable = false)
    private String auFname;

    @Column(name = "phone", length = 12, nullable = false)
    private String phone = "UNKNOWN";

    @Column(name = "address", length = 40)
    private String address;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "zip", length = 5)
    private String zip;

    @Column(name = "contract", nullable = false)
    private Integer contract;
    @JsonIgnore
    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<TitleAuthor> titleAuthors;
    public Authors() {
    }
    public Authors(String auId, String auLname, String auFname, String phone, String address, String city, String state, String zip, Integer contract) {
        this.auId = auId;
        this.auLname = auLname;
        this.auFname = auFname;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.contract = contract;
    }
    GsonBuilderUtils;
    public String getAuId() {
        return auId;
    }

    public void setAuId(String auId) {
        this.auId = auId;
    }

    public String getAuLname() {
        return auLname;
    }

    public void setAuLname(String auLname) {
        this.auLname = auLname;
    }

    public String getAuFname() {
        return auFname;
    }

    public void setAuFname(String auFname) {
        this.auFname = auFname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

}
