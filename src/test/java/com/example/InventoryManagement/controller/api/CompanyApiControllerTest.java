package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Company;
import com.example.InventoryManagement.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CompanyApiController.class)
class CompanyApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private CompanyService companyService;

    @Test
    @DisplayName("会社一覧を取得できる")
    void testList() throws Exception {

        Company company = new Company();
        company.setId(1L);
        company.setCompanyName("OpenAI");

        when(companyService.findAll())
                .thenReturn(List.of(company));

        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].companyName").value("OpenAI"));

        verify(companyService, times(1)).findAll();
    }

    @Test
    @DisplayName("指定した会社情報を取得できる")
    void testGet() throws Exception {

        Company company = new Company();
        company.setId(1L);
        company.setCompanyName("OpenAI");

        when(companyService.findById(1L))
                .thenReturn(company);

        mockMvc.perform(get("/api/companies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.companyName").value("OpenAI"));

        verify(companyService, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("会社情報を登録できる")
    void testRegister() throws Exception {

        Company company = new Company();
        company.setCompanyName("OpenAI");

        mockMvc.perform(post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isCreated());

        verify(companyService, times(1))
                .registerCompany(any(Company.class));
    }

    @Test
    @DisplayName("会社情報を更新できる")
    void testUpdate() throws Exception {

        Company company = new Company();
        company.setCompanyName("OpenAI Updated");

        mockMvc.perform(put("/api/companies/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(company)))
                .andExpect(status().isOk());

        verify(companyService, times(1))
                .updateCompany(any(Company.class));
    }

    @Test
    @DisplayName("会社情報を削除できる")
    void testDelete() throws Exception {

        mockMvc.perform(delete("/api/companies/1"))
                .andExpect(status().isNoContent());

        verify(companyService, times(1))
                .deleteCompany(1L);
    }
}