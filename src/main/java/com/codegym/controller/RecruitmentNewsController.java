package com.codegym.controller;

import com.codegym.constant.Constant;

import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.security.jwt.JwtProvider;
import com.codegym.security.jwt.JwtTokenFilter;
import com.codegym.service.company.CompanyService;
import com.codegym.service.recruitment_news.IRecruitmentNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.codegym.constant.Constant.Status.LOCK;
import static com.codegym.constant.Constant.Status.UNLOCK;


@RestController
@RequestMapping("/recruitment-news")
@CrossOrigin("*")
public class RecruitmentNewsController {
    @Autowired
    IRecruitmentNewsService recruitmentNewsService;

    @Autowired
    private CompanyService companyService;

    @PutMapping("/update-status/{id}")
    public ResponseEntity<RecruitmentNews> updateStatus(@PathVariable Long id) {
        Optional<RecruitmentNews> recruitmentNewsOptional = recruitmentNewsService.findById(id);
        if (!recruitmentNewsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (recruitmentNewsOptional.get().getStatus().equals(LOCK)) {
                recruitmentNewsOptional.get().setStatus(UNLOCK);
            } else recruitmentNewsOptional.get().setStatus(LOCK);
        }
        return new ResponseEntity<>(recruitmentNewsService.save(recruitmentNewsOptional.get()), HttpStatus.OK);
    }

    @PutMapping("/set-propose/{id}")
    public ResponseEntity<RecruitmentNews> setProposal(@PathVariable Long id) {
        Optional<RecruitmentNews> recruitmentNews = recruitmentNewsService.findById(id);
        if (!recruitmentNews.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (recruitmentNews.get().getProposed().equals(Constant.Proposal.NO)) {
                recruitmentNews.get().setProposed(Constant.Proposal.YES);
            } else recruitmentNews.get().setProposed(Constant.Proposal.NO);
        }
        return new ResponseEntity<>(recruitmentNewsService.save(recruitmentNews.get()), HttpStatus.OK);
    }

    //Danh sach tin tuyen dung duoc de xuat
    @GetMapping("/proposal-news")
    public ResponseEntity<Iterable<RecruitmentNews>> findAllProposedRecruitmentNews() {
        return new ResponseEntity<>(recruitmentNewsService.findAllProposedRecruitmentNews(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<RecruitmentNews>> findAllRecruitmentNew() {
        Iterable<RecruitmentNews> recruitmentNews = recruitmentNewsService.findAll();
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RecruitmentNews>> findById(@PathVariable Long id) {
        Optional<RecruitmentNews> recruitmentNews = recruitmentNewsService.findById(id);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecruitmentNews> updateRecruitmentNews(@RequestBody RecruitmentNews recruitmentNews, @PathVariable Long id) {
        Optional<RecruitmentNews> recruitmentNewsOptional = recruitmentNewsService.findById(id);
        if (!recruitmentNewsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recruitmentNews.setStatus(recruitmentNewsOptional.get().getStatus());
        recruitmentNews.setProposed(recruitmentNewsOptional.get().getProposed());
        recruitmentNews.setCompany(recruitmentNewsOptional.get().getCompany());
        recruitmentNews.setId(id);
        recruitmentNewsService.save(recruitmentNews);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<RecruitmentNews> saveRecruitmentNews(@RequestBody RecruitmentNews recruitmentNews) {
        recruitmentNews.setProposed(Constant.Proposal.NO);
        recruitmentNews.setStatus(UNLOCK);
        return new ResponseEntity<>(recruitmentNewsService.save(recruitmentNews), HttpStatus.OK);
    }

    @GetMapping("/find-by-company/{id}")
    public ResponseEntity<Iterable<RecruitmentNews>> findAllByCompanyId(HttpServletRequest request, @PathVariable Long id) {
        Optional<Company> companyOptional = companyService.findById(id);
        if (!companyOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Iterable<RecruitmentNews> recruitmentNews = recruitmentNewsService.findAllByCompany(companyOptional.get());
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }

    //danh sach tin tuyen dung bi khoa
    @GetMapping("/locked-list")
    public ResponseEntity<Iterable<RecruitmentNews>> findAllLockedRecruitmentNews() {
        return new ResponseEntity<>(recruitmentNewsService.findAllLockedRecruitmentNews(), HttpStatus.OK);
    }

    //danh sach tin tuyen dung khong khoa
    @GetMapping("/unlocked-list")
    public ResponseEntity<Iterable<RecruitmentNews>> findAllUnlockRecruitmentNews() {
        return new ResponseEntity<>(recruitmentNewsService.findAllUnlockRecruitmentNews(), HttpStatus.OK);
    }
}
