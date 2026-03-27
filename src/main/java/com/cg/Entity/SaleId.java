package com.cg.Entity;

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
    private String storId;

    @Column(name = "ord_num", length = 20)
    private String ordNum;

    @Column(name = "title_id", length = 10)
    private String titleId;

//    public SaleId() {}
//
//    public SaleId(String storId, String ordNum, String titleId) {
//        this.storId = storId;
//        this.ordNum = ordNum;
//        this.titleId = titleId;
//    }
//    public String getStorId() { return storId; }
//    public void setStorId(String storId) { this.storId = storId; }
//
//    public String getOrdNum() { return ordNum; }
//    public void setOrdNum(String ordNum) { this.ordNum = ordNum; }
//
//    public String getTitleId() { return titleId; }
//    public void setTitleId(String titleId) { this.titleId = titleId; }
//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleId)) return false;
        SaleId that = (SaleId) o;
        return Objects.equals(storId, that.storId) &&
                Objects.equals(ordNum, that.ordNum) &&
                Objects.equals(titleId, that.titleId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(storId, ordNum, titleId);
    }
}
