package com.jurecki.controllers;

import com.jurecki.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Author Dawid Jurecki on 15.01.2020
 **/
public class LoginControllerTest {

    LoginController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        controller = new LoginController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void loginPage() throws Exception {
        mockMvc.perform(get("/login_page"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage"))
                .andExpect(model().attributeExists("userKey"));
    }
    // need to resolve some problem
    /*@Test
    public void loginForm() throws Exception {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin123");

        mockMvc.perform(post("/login_page"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("userKey"))
                .andExpect(view().name("loginPage"));
    }
*/
    @Test
    public void indexPage() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
