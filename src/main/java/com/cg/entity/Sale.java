package com.cg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @EmbeddedId
    private SaleId id;

    @Column(name = "ord_date", nullable = false)
    private Timestamp ordDate;

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
    private Title title;
}