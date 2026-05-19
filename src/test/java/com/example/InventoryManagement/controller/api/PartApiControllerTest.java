package com.example.InventoryManagement.controller.api;

import com.example.InventoryManagement.entity.Part;
import com.example.InventoryManagement.service.PartService;
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


@WebMvcTest(PartApiController.class)
class PartApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PartService partService;

    @Test
    @DisplayName("部品一覧を取得できる")
    void testList() throws Exception {

        Part part = new Part();
        part.setId(1L);
        part.setPartName("CPU");

        when(partService.findAll())
                .thenReturn(List.of(part));

        mockMvc.perform(get("/api/parts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].partName").value("CPU"));

        verify(partService, times(1)).findAll();
    }

    @Test
    @DisplayName("指定した部品情報を取得できる")
    void testGet() throws Exception {

        Part part = new Part();
        part.setId(1L);
        part.setPartName("CPU");

        when(partService.findById(1L))
                .thenReturn(part);

        mockMvc.perform(get("/api/parts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.partName").value("CPU"));

        verify(partService, times(1))
                .findById(1L);
    }

    @Test
    @DisplayName("部品情報を登録できる")
    void testRegister() throws Exception {

        Part part = new Part();
        part.setPartName("CPU");
        part.setModelNumber("CPU-001");
        part.setStockQuantity(10);

        mockMvc.perform(post("/api/parts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(part)))
                .andExpect(status().isCreated());

        verify(partService, times(1))
                .registerPart(any(Part.class));
    }

    @Test
    @DisplayName("部品情報を更新できる")
    void testUpdate() throws Exception {

        Part part = new Part();
        part.setPartName("GPU");
        part.setModelNumber("GPU-001");
        part.setStockQuantity(5);

        mockMvc.perform(put("/api/parts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(part)))
                .andExpect(status().isOk());

        verify(partService, times(1))
                .updatePart(any(Part.class));
    }

    @Test
    @DisplayName("部品情報を削除できる")
    void testDelete() throws Exception {

        mockMvc.perform(delete("/api/parts/1"))
                .andExpect(status().isNoContent());

        verify(partService, times(1))
                .deletePart(1L);
    }
}