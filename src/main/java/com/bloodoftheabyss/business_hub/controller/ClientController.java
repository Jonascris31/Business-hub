package com.bloodoftheabyss.business_hub.controller;

import com.bloodoftheabyss.business_hub.dto.ClientRequest;
import com.bloodoftheabyss.business_hub.dto.ClientResponse;
import com.bloodoftheabyss.business_hub.entity.Client;
import com.bloodoftheabyss.business_hub.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse create(@Valid @RequestBody ClientRequest request) {

        Client client = new Client();

        client.setName(request.name());
        client.setEmail(request.email());
        client.setPhone(request.phone());

        Client savedClient = clientService.create(client);

        return ClientResponse.fromEntity(savedClient);
    }

    @GetMapping
    public List<ClientResponse> findAll() {

        return clientService.findAll()
                .stream()
                .map(ClientResponse::fromEntity)
                .toList();
    }
    
   @GetMapping("/{id}")
   public ClientResponse findById(@PathVariable Long id) {

         Client client = clientService.findById(id);

         return ClientResponse.fromEntity(client);
   }
   @PutMapping("/{id}")
   public ClientResponse update(
        @PathVariable Long id,
        @Valid @RequestBody ClientRequest request) {

    Client client = new Client();

    client.setName(request.name());
    client.setEmail(request.email());
    client.setPhone(request.phone());

    Client updatedClient = clientService.update(id, client);

    return ClientResponse.fromEntity(updatedClient);
 }
       @DeleteMapping("/{id}")
       @ResponseStatus(HttpStatus.NO_CONTENT)
       public void delete(@PathVariable Long id) {

         clientService.delete(id);
}
}