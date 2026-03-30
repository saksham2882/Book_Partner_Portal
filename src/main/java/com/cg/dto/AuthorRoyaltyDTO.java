package com.cg.dto;


public class AuthorRoyaltyDTO {

    private String authorId;
    private String authorName;
    private String titleId;
    private String titleName;
    private Integer minRoyalty;
    private Integer maxRoyalty;

    public AuthorRoyaltyDTO(String authorId, String authorName,
                            String titleId, String titleName,
                            Integer minRoyalty, Integer maxRoyalty) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.titleId = titleId;
        this.titleName = titleName;
        this.minRoyalty = minRoyalty;
        this.maxRoyalty = maxRoyalty;
    }

    public String getAuthorId() { return authorId; }
    public String getAuthorName() { return authorName; }
    public String getTitleId() { return titleId; }
    public String getTitleName() { return titleName; }
    public Integer getMinRoyalty() { return minRoyalty; }
    public Integer getMaxRoyalty() { return maxRoyalty; }
}
