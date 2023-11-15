package fr.iocean.bestioles.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

import fr.iocean.bestioles.entity.Person;
import fr.iocean.bestioles.repository.PersonRepository;
import fr.iocean.bestioles.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


//        (
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = BestiolesApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerIntegrationTest {
    private static final String ENTITY_API_URL = "/api/person";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findByIdTest() throws Exception {
        mockMvc.perform(
                        get(ENTITY_API_URL_ID, 100)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname", is("Henri")))
                .andExpect(jsonPath("$.lastname", is("Lamarque")));
    }

    @Test
    public void findById_idNotExistingTest() throws Exception {
        mockMvc.perform(
                        get(ENTITY_API_URL_ID, 1919)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode", is(404)));
    }

    @Test
    public void createTest() throws Exception {
        long countPersons = personRepository.count();

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

        assertEquals(personRepository.count(), countPersons + 1, "The count should increase by 1");
    }

    @Test
    public void create_withValidationFailTest() throws Exception {

         // Invalid Firstname
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

        // Invalid Lastname
        personToCreate.setFirstname("Created");
        personToCreate.setLastname("");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isBadRequest());

        // Invalid Age
        personToCreate.setAge(314);
        personToCreate.setLastname("Person");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    public void create_withIdTest() throws Exception {
        // Invalid because entity has an ID
        Person personToCreate = new Person();
        personToCreate.setId(2);
        personToCreate.setAge(1);
        personToCreate.setFirstname("Created");
        personToCreate.setLastname("Person");
        mockMvc.perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToCreate))
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    @Transactional
    public void update_withValidationFailTest() throws Exception {

        // Invalid Firstname
        Person personToUpdate = personRepository.findById(800).orElseThrow();
        personToUpdate.setAge(1);
        personToUpdate.setFirstname("");
        personToUpdate.setLastname("Person");
        mockMvc.perform(
                        put(ENTITY_API_URL_ID, 800)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToUpdate))
                )
                .andExpect(status().isBadRequest());

        // Invalid Lastname
        personToUpdate.setFirstname("Created");
        personToUpdate.setLastname("");
        mockMvc.perform(
                        put(ENTITY_API_URL_ID, 800)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToUpdate))
                )
                .andExpect(status().isBadRequest());

        // Invalid Age
        personToUpdate.setAge(314);
        personToUpdate.setLastname("Person");
        mockMvc.perform(
                        put(ENTITY_API_URL_ID, 800)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(JsonUtils.convertToJsonBytes(personToUpdate))
                )
                .andExpect(status().isBadRequest());
    }
}
