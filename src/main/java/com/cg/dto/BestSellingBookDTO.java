package com.cg.dto;


public class BestSellingBookDTO {

    private String authorId;
    private String authorName;
    private String titleId;
    private String titleName;
    private Long totalSales;
    private Double revenue;

    public BestSellingBookDTO(String authorId, String authorName,
                              String titleId, String titleName,
                              Long totalSales, Double revenue) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.titleId = titleId;
        this.titleName = titleName;
        this.totalSales = totalSales;
        this.revenue = revenue;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitleId() {
        return titleId;
    }

    public String getTitleName() {
        return titleName;
    }

    public Long getTotalSales() {
        return totalSales;
    }

    public Double getRevenue() {
        return revenue;
    }
}