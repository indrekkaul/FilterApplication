package com.example.filters.service;

import com.example.filters.exception.CustomServiceException;
import com.example.filters.model.Criteria;
import com.example.filters.repository.CriteriaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class CriteriaService {

    private final CriteriaRepository criteriaRepository;

    public List<Criteria> getAllCriteria() {
        try {
            return criteriaRepository.findAll();
        } catch (DataAccessException e) {
            log.error("Error retrieving criteria", e);
            throw new CustomServiceException("Could not retrieve criteria", e);
        }
    }

    @Transactional
    public Criteria createCriteria(Criteria criteria) {
        try {
            return criteriaRepository.saveAndFlush(criteria);
        } catch (DataAccessException e) {
            log.error("Error creating criteria", e);
            throw new CustomServiceException("Could not create criteria", e);
        }
    }

    public void deleteCriteria(Long id) {
        try {
            criteriaRepository.deleteById(id);
        } catch (DataAccessException e) {
            log.error("Error deleting criteria with id: " + id, e);
            throw new CustomServiceException("Could not delete criteria", e);
        }
    }
}

