//package com.unibuc.ro.utils;
//
//import com.unibuc.ro.domain.dto.ClientDto;
//import com.unibuc.ro.domain.model.Client;
//
//public class ClientMapper {
//    public ClientDto entityToDto(Client entity) {
//        ClientDto dto = new ClientDto();
//        dto.setEmail(entity.getEmail());
//        dto.setPhoneNumber(entity.getPhoneNumber());
//        dto.setFirstName(entity.getFirstName());
//        dto.setLastName(entity.getLastName());
//        return dto;
//    }
//
//    public Client dtoToEntity(ClientDto dto) {
//        Client entity = new Client();
//        entity.setEmail(dto.getEmail());
//        entity.setPhoneNumber(dto.getPhoneNumber());
//        entity.setFirstName(dto.getFirstName());
//        entity.setLastName(dto.getLastName());
//        return entity;
//    }
//}
