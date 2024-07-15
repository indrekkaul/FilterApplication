package com.example.filters.configuration;

import com.example.filters.model.ComparisonType;
import com.example.filters.model.Criteria;
import com.example.filters.model.Filter;
import com.example.filters.model.SearchBy;
import com.example.filters.service.CriteriaService;
import com.example.filters.service.FilterService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoder {

    private JdbcTemplate jdbcTemplate;
    private CriteriaService criteriaService;
    private FilterService filterService;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeData(){

        Filter filter = new Filter();
        filter.setFilterName("Default Filter");
        filterService.createFilter(filter);

        Criteria criteria1 = new Criteria();
        criteria1.setSearchBy(SearchBy.TITLE);
        criteria1.setComparisonType(ComparisonType.CONTAINS);
        criteria1.setSearchValue("Order");
        criteria1.setFilter(filter);
        criteriaService.createCriteria(criteria1);

        Criteria criteria2 = new Criteria();
        criteria2.setSearchBy(SearchBy.AMOUNT);
        criteria2.setComparisonType(ComparisonType.GREATER_THAN);
        criteria2.setSearchValue("1000");
        criteria2.setFilter(filter);
        criteriaService.createCriteria(criteria2);

        Filter filter2 = new Filter();
        filter2.setFilterName("Second Default Filter");
        filterService.createFilter(filter2);

        Criteria criteria3 = new Criteria();
        criteria3.setSearchBy(SearchBy.TITLE);
        criteria3.setComparisonType(ComparisonType.CONTAINS);
        criteria3.setSearchValue("2");
        criteria3.setFilter(filter2);
        criteriaService.createCriteria(criteria3);

        Criteria criteria4 = new Criteria();
        criteria4.setSearchBy(SearchBy.AMOUNT);
        criteria4.setComparisonType(ComparisonType.LESS_THAN);
        criteria4.setSearchValue("10");
        criteria4.setFilter(filter2);
        criteriaService.createCriteria(criteria4);
    }
}
