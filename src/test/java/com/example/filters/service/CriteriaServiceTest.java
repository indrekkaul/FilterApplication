package com.example.filters.service;

import com.example.filters.exception.CustomServiceException;
import com.example.filters.model.Criteria;
import com.example.filters.repository.CriteriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriteriaServiceTest {

    @Mock
    private CriteriaRepository criteriaRepository;

    @InjectMocks
    private CriteriaService criteriaService;

    private Criteria criteria1;
    private Criteria criteria2;

    @BeforeEach
    void setUp() {
        criteria1 = new Criteria();
        criteria1.setId(1L);
        criteria1.setSearchValue("value1");

        criteria2 = new Criteria();
        criteria2.setId(2L);
        criteria2.setSearchValue("value2");
    }

    @Test
    void testGetAllCriteriaWhenCriteriaRepositoryReturnsListThenReturnList() {
        List<Criteria> criteriaList = Arrays.asList(criteria1, criteria2);
        when(criteriaRepository.findAll()).thenReturn(criteriaList);

        List<Criteria> result = criteriaService.getAllCriteria();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(criteriaList, result);
        verify(criteriaRepository, times(1)).findAll();
    }

    @Test
    void testCreateCriteriaWhenCriteriaRepositorySavesCriteriaThenReturnSavedCriteria() {
        when(criteriaRepository.saveAndFlush(criteria1)).thenReturn(criteria1);

        Criteria result = criteriaService.createCriteria(criteria1);

        assertNotNull(result);
        assertEquals(criteria1, result);
        verify(criteriaRepository, times(1)).saveAndFlush(criteria1);
    }

    @Test
    void testDeleteCriteriaWhenCriteriaRepositoryDeletesCriteriaThenDeleteByIdCalled() {
        Long criteriaId = 1L;
        doNothing().when(criteriaRepository).deleteById(criteriaId);

        criteriaService.deleteCriteria(criteriaId);

        verify(criteriaRepository, times(1)).deleteById(criteriaId);
    }

    @Test
    void testGetAllCriteriaWhenCriteriaRepositoryThrowsExceptionThenThrowCustomServiceException() {
        when(criteriaRepository.findAll()).thenThrow(new DataAccessException("...") {});

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            criteriaService.getAllCriteria();
        });

        assertEquals("Could not retrieve criteria", exception.getMessage());
        verify(criteriaRepository, times(1)).findAll();
    }

    @Test
    void testCreateCriteriaWhenCriteriaRepositoryThrowsExceptionThenThrowCustomServiceException() {
        when(criteriaRepository.saveAndFlush(criteria1)).thenThrow(new DataAccessException("...") {});

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            criteriaService.createCriteria(criteria1);
        });

        assertEquals("Could not create criteria", exception.getMessage());
        verify(criteriaRepository, times(1)).saveAndFlush(criteria1);
    }

    @Test
    void testDeleteCriteriaWhenCriteriaRepositoryThrowsExceptionThenThrowCustomServiceException() {
        Long criteriaId = 1L;
        doThrow(new DataAccessException("...") {}).when(criteriaRepository).deleteById(criteriaId);

        CustomServiceException exception = assertThrows(CustomServiceException.class, () -> {
            criteriaService.deleteCriteria(criteriaId);
        });

        assertEquals("Could not delete criteria", exception.getMessage());
        verify(criteriaRepository, times(1)).deleteById(criteriaId);
    }
}
