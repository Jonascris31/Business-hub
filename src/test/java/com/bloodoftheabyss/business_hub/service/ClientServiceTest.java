package com.bloodoftheabyss.business_hub.service;

import com.bloodoftheabyss.business_hub.entity.Client;
import com.bloodoftheabyss.business_hub.repository.ClientRepository;
import com.bloodoftheabyss.business_hub.exception.DuplicateResourceException;
import com.bloodoftheabyss.business_hub.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(
                "Cliente Teste",
                "teste@exemplo.com",
                "31999999999"
        );
    }

    @Test
    void deveCriarClienteQuandoEmailNaoExiste() {

        when(clientRepository.existsByEmail(client.getEmail()))
                .thenReturn(false);

        when(clientRepository.save(client))
                .thenReturn(client);

        Client result = clientService.create(client);

        assertNotNull(result);
        assertEquals("Cliente Teste", result.getName());
        assertEquals("teste@exemplo.com", result.getEmail());

        verify(clientRepository).save(client);
    }

    @Test
    void naoDeveCriarClienteComEmailDuplicado() {

        when(clientRepository.existsByEmail(client.getEmail()))
                .thenReturn(true);

        DuplicateResourceException exception = assertThrows(
                DuplicateResourceException.class,
                () -> clientService.create(client)
        );

        assertEquals("Email já cadastrado.", exception.getMessage());

        verify(clientRepository, never()).save(any());
    }

    @Test
    void deveEncontrarClientePorId() {

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        Client result = clientService.findById(1L);

        assertNotNull(result);
        assertEquals("teste@exemplo.com", result.getEmail());

        verify(clientRepository).findById(1L);
    }

    @Test
    void deveRetornarErroQuandoClienteNaoExiste() {

        when(clientRepository.findById(999L))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> clientService.findById(999L)
        );

        assertEquals("Cliente não encontrado.", exception.getMessage());
    }
}