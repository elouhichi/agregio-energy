package com.agregio.energy;

import com.agregio.energy.model.Parc;
import com.agregio.energy.model.TypeParc;
import com.agregio.energy.repository.ParcRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    ParcRepository parcRepository;

    @BeforeEach
    void setUp() {
        Parc parc = new Parc();
        parc.setNom("Parc 1");
        parc.setType(TypeParc.SOLAIRE);
        parc.setCapacite(100.0);
        parcRepository.save(parc); // ID auto-généré : 1
    }

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
