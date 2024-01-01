package com.blog.database.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.database.models.MessageModel;
import com.blog.database.repositories.MessageRepository;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository messageRepository){
        this.repository = messageRepository;
    }

    @Override
    public Page<MessageModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Override
    public Object save(MessageModel messageModel){
        return repository.save(messageModel);
    }

    @Override
    public void delete(MessageModel messageModel){
        repository.delete(messageModel);
    }

    @Override
    public boolean existsById(UUID id){
        Optional<MessageModel> optional = repository.findById(id);
        if (optional.isPresent()) return true;
        return false;
    }

    @Override
    public MessageModel findById(UUID id){
        Optional<MessageModel> optional = repository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void deleteAll(){
        repository.deleteAll();
    }

}
