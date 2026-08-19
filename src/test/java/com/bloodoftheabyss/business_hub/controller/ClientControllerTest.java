package com.bloodoftheabyss.business_hub.controller;

import com.bloodoftheabyss.business_hub.entity.Client;
import com.bloodoftheabyss.business_hub.service.ClientService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Test
    void deveCriarCliente() throws Exception {

        Client client = new Client(
                "Cliente Teste",
                "teste@exemplo.com",
                "31999999999"
        );

        when(clientService.create(any(Client.class)))
                .thenReturn(client);

        String json = """
                {
                    "name": "Cliente Teste",
                    "email": "teste@exemplo.com",
                    "phone": "31999999999"
                }
                """;

        mockMvc.perform(
                post("/api/clients")
                        .contentType("application/json")
                        .content(json)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Cliente Teste"))
        .andExpect(jsonPath("$.email").value("teste@exemplo.com"));
    }

    @Test
    void deveListarClientes() throws Exception {

        Client client = new Client(
                "Cliente Teste",
                "teste@exemplo.com",
                "31999999999"
        );

        when(clientService.findAll())
                .thenReturn(List.of(client));

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Cliente Teste"))
                .andExpect(jsonPath("$[0].email").value("teste@exemplo.com"));
    }
}