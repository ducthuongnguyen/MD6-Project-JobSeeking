package com.codegym.controller;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Message;
import com.codegym.service.company.CompanyService;
import com.codegym.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/message")
@CrossOrigin("*")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private CompanyService companyService;
    @GetMapping
    public ResponseEntity<Iterable<Message>> findAll() {
        Iterable<Message> messages = messageService.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Message>> findById(@PathVariable Long id) {
        Optional<Message> messages = messageService.findById(id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<Message> add(@RequestBody Message message) {
        message.setContent(" đã ứng tuyển vào tin tuyển ");
        messageService.save(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        Optional<Message> message = messageService.findById(id);
        if (!message.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        messageService.remove(id);
        return new ResponseEntity<>(message.get(), HttpStatus.OK);
    }
    @GetMapping("/findByCompany/{id}")
    public ResponseEntity<Iterable<Message>> findByCompany(@PathVariable Long id){
        Optional<Company> company = companyService.findById(id);
        return new ResponseEntity<>(messageService.findAllByCompany(company.get()), HttpStatus.OK);
    }
}
