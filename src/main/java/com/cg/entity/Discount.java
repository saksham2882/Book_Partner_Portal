package com.cg.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "discounts")
@Data
public class Discount {

    @Id
    @Column(name = "discounttype", length = 40, nullable = false)
    private String discountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stor_id")
    private Store store;

    @Column(name = "lowqty")
    private Short lowQty;

    @Column(name = "highqty")
    private Short highQty;

    @Column(name = "discount", nullable = false, precision = 4, scale = 2, columnDefinition = "DECIMAL(4,2)")
    private BigDecimal discount;
}