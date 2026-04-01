package com.cg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleId implements Serializable {
    @Column(name = "stor_id", length = 4)
    private String storeId;

    @Column(name = "ord_num", length = 20)
    private String ordNum;

    @Column(name = "title_id", length = 10)
    private String titleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleId)) return false;
        SaleId that = (SaleId) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(ordNum, that.ordNum) &&
                Objects.equals(titleId, that.titleId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(storeId, ordNum, titleId);
    }
}