package com.blog.database.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.database.models.MessageModel;

import io.micrometer.common.lang.NonNullApi;

@NonNullApi
public interface MessageRepository extends JpaRepository<MessageModel, UUID> {

    Page<MessageModel> findAll(Pageable pageable);

    boolean existsById(UUID id);

}
