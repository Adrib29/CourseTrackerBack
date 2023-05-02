package com.back.coursetracker;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CoursetrackerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetParcours() throws Exception {
        mockMvc.perform(get("/parcours"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].nom", is("G34")));
    }

}