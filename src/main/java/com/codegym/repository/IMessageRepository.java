package com.codegym.repository;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {

    Iterable<Message> findAllByCompany(Company company);
}
