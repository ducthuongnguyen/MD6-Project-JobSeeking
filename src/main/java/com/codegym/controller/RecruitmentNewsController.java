package com.codegym.controller;

import com.codegym.constant.Constant;

import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.recruitment_news.IRecruitmentNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.codegym.constant.Constant.Status.LOCK;
import static com.codegym.constant.Constant.Status.UNLOCK;


@RestController
@RequestMapping("/recruitment-news")
@CrossOrigin("*")
public class RecruitmentNewsController {
    @Autowired
    IRecruitmentNewsService recruitmentNewsService;

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
        recruitmentNews.setId(id);
        recruitmentNewsService.save(recruitmentNews);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<RecruitmentNews> saveRecruitmentNews(@RequestBody RecruitmentNews recruitmentNews) {
        return new ResponseEntity<>(recruitmentNewsService.save(recruitmentNews), HttpStatus.OK);
    }
}
