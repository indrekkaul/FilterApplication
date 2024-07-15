package com.example.filters.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filterName;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Criteria> criteriaList = new ArrayList<>();

}
