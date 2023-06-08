package com.example.app.controllers;

import com.example.app.models.LoginRequest;
import com.example.app.models.User;
import com.example.app.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserRepository userRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void createUser_validUser_userCreated() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        String body = objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    void getAllUsers_validRequest_returnAllUsers() throws Exception {
        // Create multiple users in the database
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setPassword("password123");
        userRepository.save(user2);

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"))
                .andExpect(jsonPath("$[1].email").value("jane.smith@example.com"))
                .andReturn();
    }

    @Test
    void getUserById_existingId_returnUser() throws Exception {
        // Create a user in the database
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        User createdUser = userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", createdUser.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andReturn();
    }

    @Test
    void getUserById_nonExistingId_returnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 123))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void updateUser_existingId_validUser_userUpdated() throws Exception {
        // Create a user in the database
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        User createdUser = userRepository.save(user);

        // Update the user
        createdUser.setName("Jane Smith");
        createdUser.setEmail("jane.smith@example.com");
        createdUser.setPassword("password123");

        String body = objectMapper.writeValueAsString(createdUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", createdUser.getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"))
                .andReturn();
    }

    @Test
    void updateUser_nonExistingId_returnNotFound() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password345");

        String body = objectMapper.writeValueAsString(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", 123)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void deleteUser_existingId_userDeleted() throws Exception {
        // Create a user in the database
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        User createdUser = userRepository.save(user);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", createdUser.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void deleteUser_nonExistingId_returnNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 123))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void login_validCredentials_returnToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "password123");

        String body = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").isString())
                .andReturn();
    }

    @Test
    void login_invalidCredentials_returnUnauthorized() throws Exception {
        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "invalidpassword");

        String body = objectMapper.writeValueAsString(loginRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }

}
