package com.cg.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roysched")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoySched {

    @Id
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
    private Title title;
}
