package cz.frantisekfilip.ticketsandbets.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;
import cz.frantisekfilip.ticketsandbets.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class UserControllerIntergationTests {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private UserService userService;

    @Autowired
    public UserControllerIntergationTests(MockMvc mockMvc, UserService userService){
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.userService = userService;
    }

    @Test
    public void testThatCretaUserSuccesfullyReturnsHttp201Created() throws Exception {
        UserEntity testUserA = UserEntity.builder()
                .username("filipfr")
                .password("123456789")
                .email("filipfr@seznam.cz")
                .build();
        String testUserAAsJson = objectMapper.writeValueAsString(testUserA);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testUserAAsJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCretaUserSuccesfullyReturnsSavedUser() throws Exception {
        UserEntity testUserA = UserEntity.builder()
                .username("filipfr")
                .password("123456789")
                .email("filipfr@seznam.cz")
                .build();
        String testUserAAsJson = objectMapper.writeValueAsString(testUserA);
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testUserAAsJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value("filipfr")
        );
    }

    @Test
    public void testThatFindAllSuccesfullyReturnsHttp200Ok() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindByIdSuccesfullyReturnsHttp200Ok() throws Exception {
        UserEntity testUserA = UserEntity.builder()
                .username("filipfr")
                .password("123456789")
                .email("filipfr@seznam.cz")
                .build();
        userService.save(testUserA);
        String testUserAAsJson = objectMapper.writeValueAsString(testUserA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFindByIdSuccesfullyReturnsPassedUser() throws Exception {
        UserEntity testUserA = UserEntity.builder()
                .username("filipfr")
                .password("123456789")
                .email("filipfr@seznam.cz")
                .build();
        userService.save(testUserA);
        String testUserAAsJson = objectMapper.writeValueAsString(testUserA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value("filipfr")
        );
    }

    @Test
    public void testThatDeleteByIdSuccesfullyReturnsHttp404NotFound() throws Exception {
        UserEntity testUserA = UserEntity.builder()
                .username("filipfr")
                .password("123456789")
                .email("filipfr@seznam.cz")
                .build();
        UserEntity savedUserA = userService.save(testUserA);

        UserEntity testUserB = UserEntity.builder()
                .username("filipfranta")
                .password("123456789")
                .email("filipfr@seznam.cz")
                .build();
        userService.save(testUserB);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/users/3")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/users/" + savedUserA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNoContent()
        );
    }
}
