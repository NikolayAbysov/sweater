package com.example.sweater.repository;

import com.example.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;
import java.awt.*;
import java.util.List;


public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag /*Название метода определяет его функции см. keywords inside method names Spring*/ (String tag);
}
