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
public class OffreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createOffre() throws Exception {
        String json = """
            {
              "marche": "PRIMAIRE",
              "blocs": [
                {
                  "heureDebut": 0,
                  "heureFin": 3,
                  "puissanceMW": 50,
                  "prixPlancher": 45.5,
                  "parcIds": [1]
                }
              ]
            }
        """;

        mockMvc.perform(post("/offres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.marche").value("PRIMAIRE"))
                .andExpect(jsonPath("$.blocs.length()").value(1));
    }

    @Test
    void offresForMarche() throws Exception {
        mockMvc.perform(get("/offres?marche=PRIMAIRE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void createOffreFailWithInvalidMarche() throws Exception {
        String json = """
            {
              "marche": "NAN",
              "blocs": []
            }
        """;

        mockMvc.perform(post("/offres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createOffreFailWithoutBlocs() throws Exception {
        String json = """
            {
              "marche": "PRIMAIRE",
              "blocs": []
            }
        """;

        mockMvc.perform(post("/offres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
