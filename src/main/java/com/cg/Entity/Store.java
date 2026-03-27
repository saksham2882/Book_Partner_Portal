package com.cg.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "stores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @Column(name = "stor_id", length = 4)
    private String storId;

    @Column(name = "stor_name", length = 40)
    private String storName;

    @Column(name = "stor_address", length = 40)
    private String storAddress;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "zip", length = 5)
    private String zip;
    @JsonIgnore
    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Sale> sales;
    @JsonIgnore
    @OneToMany(
            mappedBy = "store",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Discount> discounts;
//    public Store() {}
//
//    public Store(String storId, String storName, String storAddress,
//                 String city, String state, String zip) {
//        this.storId = storId;
//        this.storName = storName;
//        this.storAddress = storAddress;
//        this.city = city;
//        this.state = state;
//        this.zip = zip;
//    }
//    public String getStorId() {
//        return storId;
//    }
//
//    public void setStorId(String storId) {
//        this.storId = storId;
//    }
//
//    public String getStorName() {
//        return storName;
//    }
//
//    public void setStorName(String storName) {
//        this.storName = storName;
//    }
//
//    public String getStorAddress() {
//        return storAddress;
//    }
//
//    public void setStorAddress(String storAddress) {
//        this.storAddress = storAddress;
//    }
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getZip() {
//        return zip;
//    }
//    public void setZip(String zip) {
//        this.zip = zip;
//    }
//
//    public List<Sale> getSales() {
//        return sales;
//    }
//
//    public void setSales(List<Sale> sales) {
//        this.sales = sales;
//    }
//
//    public List<Discount> getDiscounts() {
//        return discounts;
//    }
//
//    public void setDiscounts(List<Discount> discounts) {
//        this.discounts = discounts;
//    }
}
