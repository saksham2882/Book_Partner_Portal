package com.cg.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roysched")
public class RoySched {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roysched_id")
    private Integer royschedId;

    @Column(name = "lorange")
    private Integer loRange;

    @Column(name = "hirange")
    private Integer hiRange;

    @Column(name = "royalty")
    private Integer royalty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Titles title;
    public RoySched() {}

    public RoySched(Integer loRange, Integer hiRange,
                    Integer royalty, Titles title) {
        this.loRange = loRange;
        this.hiRange = hiRange;
        this.royalty = royalty;
        this.title = title;
    }


    public Integer getRoyschedId() {
        return royschedId;
    }

    public void setRoyschedId(Integer royschedId) {
        this.royschedId = royschedId;
    }

    public Integer getLoRange() {
        return loRange;
    }

    public void setLoRange(Integer loRange) {
        this.loRange = loRange;
    }

    public Integer getHiRange() {
        return hiRange;
    }

    public void setHiRange(Integer hiRange) {
        this.hiRange = hiRange;
    }

    public Integer getRoyalty() {
        return royalty;
    }

    public void setRoyalty(Integer royalty) {
        this.royalty = royalty;
    }

    public Titles getTitle() {
        return title;
    }

    public void setTitle(Titles title) {
        this.title = title;
    }
}
