package com.example.filters.controller;

import com.example.filters.model.Filter;
import com.example.filters.service.FilterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(FilterController.class)
public class FilterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilterService filterService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllFiltersWhenFiltersExistThenReturnFilters() throws Exception {
        Filter filter1 = new Filter();
        filter1.setId(1L);
        filter1.setFilterName("Filter1");

        Filter filter2 = new Filter();
        filter2.setId(2L);
        filter2.setFilterName("Filter2");

        List<Filter> filters = Arrays.asList(filter1, filter2);
        when(filterService.getAllFilters()).thenReturn(filters);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/filters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].filterName").value("Filter1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].filterName").value("Filter2"));
    }

    @Test
    public void testGetAllFiltersWhenNoFiltersExistThenReturnNoContent() throws Exception {
        when(filterService.getAllFilters()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/filters"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testCreateFilter() throws Exception {
        Filter filter = new Filter();
        filter.setId(1L);
        filter.setFilterName("New Filter");

        when(filterService.createFilter(filter)).thenReturn(filter);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/filters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(filter)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filterName").value("New Filter"));
    }

    @Test
    public void testDeleteFilter() throws Exception {
        Long filterId = 1L;
        doNothing().when(filterService).deleteFilter(filterId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/filters/{id}", filterId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
