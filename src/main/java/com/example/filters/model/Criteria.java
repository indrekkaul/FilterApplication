package com.example.filters.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Criteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Filter filter;

    private SearchBy searchBy;
    private ComparisonType comparisonType;
    private String searchValue;
}
