package com.example.filters.service;

import com.example.filters.exception.CustomServiceException;
import com.example.filters.model.Filter;
import com.example.filters.repository.FilterRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;

    public List<Filter> getAllFilters() {
        try {
            return filterRepository.findAll();
        } catch (DataAccessException e) {
            log.error("Error retrieving filters", e);
            throw new CustomServiceException("Could not retrieve filters", e);
        }
    }

    @Transactional
    public Filter createFilter(Filter filter) {
        try {
            return filterRepository.saveAndFlush(filter);
        } catch (DataAccessException e) {
            log.error("Error creating filter", e);
            throw new CustomServiceException("Could not create filter", e);
        }
    }

    public void deleteFilter(Long id) {
        try {
            filterRepository.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Error deleting filter with id: " + id, e);
            throw new CustomServiceException("Could not delete filter", e);
        }
    }
}
