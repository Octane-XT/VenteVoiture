package com.CarSelling.project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.CarSelling.project.model.Message;
import com.CarSelling.project.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Message message) {
        return  messageRepository.save(message);
    }

    public List<Message> getMessagesByRecipient(String recipient) {
        return messageRepository.findByRecipient(recipient);
    }

    public List<Message> getMessageByDiscussion(Integer iddiscussion){
        return messageRepository.findByIddiscussion(iddiscussion);
    }

    // public List<Message> getAllMessageSender(Integer iddiscussion, String sender){
    //     return messageRepository.findByIddiscussionSender(iddiscussion, sender);
    // }

    // public List<Message> getAllMessageRecipient(Integer iddiscussion, String recipient){
    //     return messageRepository.findByIddiscussionRecipient(iddiscussion, recipient);
    // }
}
