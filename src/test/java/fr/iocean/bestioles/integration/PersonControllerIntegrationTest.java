package fr.iocean.bestioles.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
//        (
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = BestiolesApplication.class)
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {
    private static final String ENTITY_API_URL = "/api/person";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(
                        get(ENTITY_API_URL_ID, 100)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname", is("Henri")))
                .andExpect(jsonPath("$.lastname", is("Lamarque")));
    }

    @Test
    public void findById_idNotExistingTest() throws Exception {
        mockMvc.perform(
                        get(ENTITY_API_URL_ID, 1919)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    public void createTest() throws Exception {
        Person personToCreate = new Person();
        personToCreate.setAge(1);
        personToCreate.setFirstname("Created");
        personToCreate.setLastname("Person");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void create_withValidationFailTest() throws Exception {
        Person personToCreate = new Person();
        personToCreate.setAge(1);
        personToCreate.setFirstname("");
        personToCreate.setLastname("Person");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isBadRequest());

        personToCreate.setFirstname("Created");
        personToCreate.setLastname("");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isBadRequest());

        personToCreate.setAge(314);
        personToCreate.setLastname("Person");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isBadRequest());
    }
}
