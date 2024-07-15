package com.example.filters.service;

import com.example.filters.exception.CustomServiceException;
import com.example.filters.model.Filter;
import com.example.filters.repository.FilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {

    @Mock
    private FilterRepository filterRepository;

    @InjectMocks
    private FilterService filterService;

    private Filter filter1;
    private Filter filter2;

    @BeforeEach
    public void setUp() {
        filter1 = new Filter();
        filter1.setId(1L);
        filter1.setFilterName("Filter1");

        filter2 = new Filter();
        filter2.setId(2L);
        filter2.setFilterName("Filter2");
    }

    @Test
    public void testGetAllFiltersWhenFiltersExistThenReturnFilters() {
        List<Filter> filters = Arrays.asList(filter1, filter2);
        when(filterRepository.findAll()).thenReturn(filters);

        List<Filter> result = filterService.getAllFilters();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(filters.size());
        assertThat(result).containsExactlyInAnyOrderElementsOf(filters);
    }

    @Test
    public void testGetAllFiltersWhenExceptionThenThrowCustomServiceException() {
        when(filterRepository.findAll()).thenThrow(new DataAccessException("...") {});

        assertThatThrownBy(() -> filterService.getAllFilters())
                .isInstanceOf(CustomServiceException.class)
                .hasMessage("Could not retrieve filters");
    }

    @Test
    public void testCreateFilter() {
        when(filterRepository.saveAndFlush(filter1)).thenReturn(filter1);

        Filter result = filterService.createFilter(filter1);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(filter1.getId());
        assertThat(result.getFilterName()).isEqualTo(filter1.getFilterName());
    }

    @Test
    public void testCreateFilterWhenExceptionThenThrowCustomServiceException() {
        when(filterRepository.saveAndFlush(filter1)).thenThrow(new DataAccessException("...") {});

        assertThatThrownBy(() -> filterService.createFilter(filter1))
                .isInstanceOf(CustomServiceException.class)
                .hasMessage("Could not create filter");
    }

    @Test
    public void testDeleteFilter() {
        Long filterId = 1L;
        doNothing().when(filterRepository).deleteById(filterId);

        filterService.deleteFilter(filterId);

        verify(filterRepository, times(1)).deleteById(filterId);
    }
}
