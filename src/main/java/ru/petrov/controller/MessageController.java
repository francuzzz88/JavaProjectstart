package ru.petrov.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.petrov.domain.Message;
import ru.petrov.domain.Views;
import ru.petrov.dto.EventType;
import ru.petrov.dto.ObjectType;
import ru.petrov.repository.MessageRepository;
import ru.petrov.util.WsSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("message")
public class MessageController {
private final MessageRepository messageRepository;
private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepository messageRepository, WsSender wsSender) {
        this.messageRepository = messageRepository;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }



    @PostMapping
    public Message create(@RequestBody Message message){
        message.setTimeCreation(LocalDateTime.now());
        Message updateMessage = messageRepository.save(message);
        wsSender.accept(EventType.CREATE, updateMessage);
        return updateMessage;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updateMessage = messageRepository.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updateMessage);
        return updateMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepository.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }


}
