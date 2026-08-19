package com.bloodoftheabyss.business_hub.dto;

import com.bloodoftheabyss.business_hub.entity.Client;

import java.time.LocalDateTime;

public record ClientResponse(
        Long id,
        String name,
        String email,
        String phone,
        LocalDateTime createdAt
) {

    public static ClientResponse fromEntity(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getCreatedAt()
        );
    }
}