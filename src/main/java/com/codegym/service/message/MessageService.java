package com.codegym.service.message;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Message;
import com.codegym.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MessageService implements IMessageService{

    @Autowired
    private IMessageRepository messageRepository;
    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void remove(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Iterable<Message> findAllByCompany(Company company) {
        return messageRepository.findAllByCompany(company);
    }
}
