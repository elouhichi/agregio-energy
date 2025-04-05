package com.agregio.energy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ParcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createParcSuccess() throws Exception {
        String json = """
            {
              "nom": "Parc Solaire",
              "type": "SOLAIRE",
              "capacite": 100.0
            }
        """;

        mockMvc.perform(post("/parcs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("Parc Solaire"));
    }

    @Test
    void createParcFailInvalidType() throws Exception {
        String json = """
            {
              "nom": "Parc Invalide",
              "type": "NUCLEAIRE",
              "capacite": 100.0
            }
        """;

        mockMvc.perform(post("/parcs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void parcsForGivenMarche() throws Exception {
        mockMvc.perform(get("/parcs?marche=PRIMAIRE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
