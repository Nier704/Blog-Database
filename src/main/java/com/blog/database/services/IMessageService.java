package com.blog.database.services;

import com.blog.database.models.MessageModel;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMessageService {

    Page<MessageModel> findAll(Pageable pageable);

    Object save(MessageModel messageModel);

    void delete(MessageModel messageModel);

    boolean existsById(UUID id);

    MessageModel findById(UUID id);

    void deleteAll();

}
