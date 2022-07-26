package com.codegym.service.message;


import com.codegym.model.entity.Company;
import com.codegym.model.entity.Message;
import com.codegym.service.IGeneralService;

public interface IMessageService extends IGeneralService<Message> {

    // tìm thông báo theo cty
    Iterable<Message> findAllByCompany(Company company);
}
