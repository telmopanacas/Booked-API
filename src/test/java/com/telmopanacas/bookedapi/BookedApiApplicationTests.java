package com.telmopanacas.bookedapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookedApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCheckAliveEndpoint() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String expectedTime = now.format(formatter);
        String expctedMessage = "It's alive! It's alive!";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/alive"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(expctedMessage))
                .andExpect(MockMvcResultMatchers.jsonPath("$.time").value(expectedTime));
    }
}
