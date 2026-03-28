package com.cg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.time.LocalDateTime;

@Entity
@Table(name="titles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Title {
    @Id
    @Column(name = "title_id", length = 10, nullable = false)
    private String titleId;
    @Column(name = "title", length = 80, nullable = false)
    private String title;

    @Column(name = "type", length = 12, nullable = false)
    private String type = "UNDECIDED";

    @Column(name = "price")
    private Double price;

    @Column(name = "advance")
    private Double advance;

    @Column(name = "royalty")
    private Integer royalty;

    @Column(name = "ytd_sales")
    private Integer ytdSales;

    @Column(name = "notes", length = 200)
    private String notes;

    @Column(name = "pubdate", nullable = false)
    private LocalDateTime pubdate;
    @ManyToOne
    @JoinColumn(name = "pub_id")
    private Publishers publisher;
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private List<TitleAuthor> titleAuthors;
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private List<Sale> sales;
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private List<RoySched> royschedList;
//    public Titles() {
//    }
//
//    public Titles(String titleId, String title, String type,
//                  Double price, Double advance, Integer royalty,
//                  Integer ytdSales, String notes, LocalDateTime pubdate) {
//        this.titleId = titleId;
//        this.title = title;
//        this.type = type;
//        this.price = price;
//        this.advance = advance;
//        this.royalty = royalty;
//        this.ytdSales = ytdSales;
//        this.notes = notes;
//        this.pubdate = pubdate;
//    }
//    public String getTitleId() {
//        return titleId;
//    }
//
//    public void setTitleId(String titleId) {
//        this.titleId = titleId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public Double getAdvance() {
//        return advance;
//    }
//
//    public void setAdvance(Double advance) {
//        this.advance = advance;
//    }
//
//    public Integer getRoyalty() {
//        return royalty;
//    }
//
//    public void setRoyalty(Integer royalty) {
//        this.royalty = royalty;
//    }
//
//    public Integer getYtdSales() {
//        return ytdSales;
//    }
//
//    public void setYtdSales(Integer ytdSales) {
//        this.ytdSales = ytdSales;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public LocalDateTime getPubdate() {
//        return pubdate;
//    }
//
//    public void setPubdate(LocalDateTime pubdate) {
//        this.pubdate = pubdate;
//    }
//
//    public Publishers getPublisher() {
//        return publisher;
//    }
//
//    public void setPublisher(Publishers publisher) {
//        this.publisher = publisher;
//    }
//
//    public List<TitleAuthor> getTitleAuthors() {
//        return titleAuthors;
//    }
//
//    public void setTitleAuthors(List<TitleAuthor> titleAuthors) {
//        this.titleAuthors = titleAuthors;
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
//    public List<RoySched> getRoyschedList() {
//        return royschedList;
//    }
//
//    public void setRoyschedList(List<RoySched> royschedList) {
//        this.royschedList = royschedList;
//    }
}

