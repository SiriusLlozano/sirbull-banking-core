package org.sirbull.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CronogramaIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Commit
    void deberiaGuardarCronogramaExitosamente() throws Exception{

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("monto", new BigDecimal("1500.00"));
        requestBody.put("cuotasTotales", 12);
        requestBody.put("diaPago", 15);
        requestBody.put("diaCierre", 18);
        requestBody.put("fechaCompra", LocalDate.now().toString());
        requestBody.put("ted", new BigDecimal("0.02"));

        mockMvc.perform(post("/api/cronograma")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isCreated()) // O isOk() dependiendo de tu API
                .andExpect(jsonPath("$.id").exists());
    }
}
