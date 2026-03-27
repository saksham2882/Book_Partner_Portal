package com.cg.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sales")
public class Sale {
    @EmbeddedId
    private SaleId id;

    @Column(name = "ord_date", nullable = false)
    private LocalDateTime ordDate;

    @Column(name = "qty", nullable = false)
    private short qty;

    @Column(name = "payterms", length = 12, nullable = false)
    private String payTerms;
    @MapsId("storId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stor_id", nullable = false)
    private Store store;
    @MapsId("titleId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", nullable = false)
    private Titles title;
    public Sale() {}

    public Sale(SaleId id, LocalDateTime ordDate,short qty, String payTerms,
                Store store, Titles title) {
        this.id = id;
        this.ordDate = ordDate;
        this.qty = qty;
        this.payTerms = payTerms;
        this.store = store;
        this.title = title;
    }
    public SaleId getId() {
        return id;
    }

    public void setId(SaleId id) {
        this.id = id;
    }

    public LocalDateTime getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(LocalDateTime ordDate) {
        this.ordDate = ordDate;
    }

    public short getQty() {
        return qty;
    }

    public void setQty(short qty) {
        this.qty = qty;
    }
    public String getPayTerms() {
        return payTerms;
    }

    public void setPayTerms(String payTerms) {
        this.payTerms = payTerms;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
