package fr.iocean.bestioles.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.iocean.bestioles.repository.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerIntegrationTest {
    private static final String ENTITY_API_URL = "/api/animal";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllTest() throws Exception {
        int countAnimals = (int) animalRepository.count();
        mockMvc.perform(
                get(ENTITY_API_URL)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(countAnimals)))
                .andExpect(jsonPath("$.[0].id", is(100)))
                ;
    }
}
