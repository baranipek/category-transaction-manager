package com.category.transaction.manager.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validCategoryTransactionId_GetCategoryTransaction_TransactionListIsReturned() throws Exception {
        mockMvc.perform(get("/api/v1/categories/a6hg1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.results", hasSize(2)))
                .andExpect(jsonPath("$.results.[0].account_id", is("fakeAcct11")))
                .andExpect(jsonPath("$.results.[0].transaction_id", is("fakeTrx01")))
                .andExpect(jsonPath("$.results.[0].amount", is(4000)))
                .andExpect(jsonPath("$.results.[0].transaction_type", is("ACTUAL")))
                .andExpect(jsonPath("$.results.[0].status", is("BOOKED")))
                .andExpect(jsonPath("$.results.[0].category", is("Revenue")));

    }

    @Test
    public void invalidCategoryTransactionId_GetCategoryTransaction_BadRequestReturned() throws Exception {
        mockMvc.perform(get("/api/v1/categories/123123123"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void validaUpdateCategoryRequest_UpdateCategory_CategoryTransactionIsUpdatedSuccessfully() throws Exception{
        mockMvc.perform(put("/api/v1/categories").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"transaction_id\": \"fakeTrx01\",\"category_id\": \"a6hg1\"}"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void inValidaUpdateCategoryRequest_UpdateCategory_BadRequestReturned() throws Exception{
        mockMvc.perform(put("/api/v1/categories").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"badField\": \"\",\"badField\": \"1231312\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void wrongUpdateCategoryRequest_UpdateCategory_BadRequestReturned() throws Exception{
        mockMvc.perform(put("/api/v1/categories").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"transaction_id\": \"randomTransaction\",\"category_id\": \"randomTransaction\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}