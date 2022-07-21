package com.codegym.service.company;


import com.codegym.model.dto.request.SignUpCompanyForm;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.model.dto.DataMailDTO;
import com.codegym.model.dto.response.CompanyResponse;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.User;
import com.codegym.repository.ICompanyRepository;
import com.codegym.service.MailService;
import com.codegym.utils.Const;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CompanyService implements ICompanyService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ICompanyRepository companyRepository;

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company save(Company company) {
        try {
            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(company.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", company.getName());
            props.put("email", company.getEmail());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return companyRepository.save(company);
        } catch (MessagingException exp) {
            exp.printStackTrace();
        }
        return companyRepository.save(company);
    }

    @Override
    public void remove(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Page<Company> findAll(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    @Override
    public Iterable<Company> findAllProposedCompanies() {
        return companyRepository.findAllProposedCompanies();
    }

    public Optional<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    public Boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    @Override
    public Optional<Company> findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public Company create(@NonNull Company command, MultipartFile file){
        Company company = Company.builder()
                .name(command.getName())
                .email(command.getEmail())
                .password(command.getPassword())
                .address(command.getAddress())
                .phoneNumber(command.getPhoneNumber())
                .introduction(command.getIntroduction())
                .build();
        String image=null;
        try {
            byte[] fileContent = file.getBytes();
            String outputFile = Base64.getEncoder().encodeToString(fileContent);
            String contentType = file.getContentType();
            image="data:".concat(contentType).concat(";base64,").concat(outputFile);

        } catch (IOException e) {
           log.info("Error in file get bytes ``", file);
        }
        company.setAvatar(image);
        return companyRepository.save(company);



    }
}
