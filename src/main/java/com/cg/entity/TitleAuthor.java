package com.cg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="titleauthor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleAuthor {
    @EmbeddedId
    private TitleAuthorId id;
    @Column(name = "au_ord")
    private Byte auOrd;

    @Column(name = "royaltyper")
    private Integer royaltyPer;
    @ManyToOne
    @MapsId("auId")   // Maps composite key field
    @JoinColumn(name = "au_id")
    private Authors author;
    @ManyToOne
    @MapsId("titleId")
    @JoinColumn(name = "title_id")
    private Title title;
//    public TitleAuthor() {
//    }
//
//    public TitleAuthor(TitleAuthorId id, Byte auOrd, Integer royaltyPer) {
//        this.id = id;
//        this.auOrd = auOrd;
//        this.royaltyPer = royaltyPer;
//    }
//    public TitleAuthorId getId() {
//        return id;
//    }
//
//    public void setId(TitleAuthorId id) {
//        this.id = id;
//    }
//
//    public Byte getAuOrd() {
//        return auOrd;
//    }
//
//    public void setAuOrd(Byte auOrd) {
//        this.auOrd = auOrd;
//    }
//
//    public Integer getRoyaltyPer() {
//        return royaltyPer;
//    }
//    public void setRoyaltyPer(Integer royaltyPer) {
//        this.royaltyPer = royaltyPer;
//    }
//
//    public Authors getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Authors author) {
//        this.author = author;
//    }
//
//    public Titles getTitle() {
//        return title;
//    }
//
//    public void setTitle(Titles title) {
//        this.title = title;
//    }
}

