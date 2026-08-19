package com.bloodoftheabyss.business_hub.service;

import com.bloodoftheabyss.business_hub.entity.Client;
import com.bloodoftheabyss.business_hub.exception.DuplicateResourceException;
import com.bloodoftheabyss.business_hub.exception.ResourceNotFoundException;
import com.bloodoftheabyss.business_hub.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {

        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new DuplicateResourceException("Email já cadastrado.");
        }

        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {

        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado."));
    }

    public Client update(Long id, Client updatedClient) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cliente não encontrado."));

        if (!client.getEmail().equals(updatedClient.getEmail())
                && clientRepository.existsByEmail(updatedClient.getEmail())) {

            throw new DuplicateResourceException("Email já cadastrado.");
        }

        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        client.setPhone(updatedClient.getPhone());

        return clientRepository.save(client);
    }

    public void delete(Long id) {

        Client client = findById(id);

        clientRepository.delete(client);
    }
}