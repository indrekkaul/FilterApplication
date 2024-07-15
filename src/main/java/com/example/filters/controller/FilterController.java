package com.example.filters.controller;

import com.example.filters.model.Filter;
import com.example.filters.exception.CustomServiceException;
import com.example.filters.service.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
@CrossOrigin(origins = "http://localhost:3000")
public class FilterController {

    private final FilterService filterService;

    @GetMapping
    public ResponseEntity<List<Filter>> getAllFilters() {
        List<Filter> filters = filterService.getAllFilters();
        if (filters.isEmpty()){
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Filter> createFilter(@RequestBody Filter filter) {
        return new ResponseEntity<>(filterService.createFilter(filter), HttpStatus.CREATED);
    }
}
