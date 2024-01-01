package com.blog.database.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.database.dtos.MessageDTO;
import com.blog.database.models.MessageModel;
import com.blog.database.services.MessageService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService messageService){
        this.service = messageService;
    }

    @GetMapping
    ResponseEntity<Page<MessageModel>> getAllMessages(@PageableDefault Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @PostMapping
    ResponseEntity<?> saveMessage(@RequestBody MessageDTO messageDTO){
        var messageModel = new MessageModel();
        BeanUtils.copyProperties(messageDTO, messageModel);
        messageModel.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(messageModel));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMessage(@PathVariable(value = "id") UUID id){
        if (service.existsById(id)){
            service.delete(service.findById(id));
            return ResponseEntity.status(HttpStatus.OK).body("Mensagem de id: " + id + " deletado.");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Mensagem de id: " + id + " nao encontrada.");
    }

    @DeleteMapping("/all")
    ResponseEntity<?> deleteAllMessages(){
        service.deleteAll();
        return ResponseEntity.ok().body("Todas as mensagens foram deletadas.");
    }

}
