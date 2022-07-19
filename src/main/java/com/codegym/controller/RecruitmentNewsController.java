package com.codegym.controller;

import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.recruitment_news.IRecruitmentNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/recruitment-news")
@CrossOrigin("*")
public class RecruitmentNewsController {
    @Autowired
    private IRecruitmentNewsService recruitmentNewsService;

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

    @PutMapping("/edit-recruitment-news/{id}")
    public ResponseEntity<RecruitmentNews> updateRecruitmentNews(@RequestBody RecruitmentNews recruitmentNews, @PathVariable Long id) {
        Optional<RecruitmentNews> recruitmentNewsOptional = recruitmentNewsService.findById(id);
        if (!recruitmentNewsOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        recruitmentNews.setId(id);
        recruitmentNewsService.save(recruitmentNews);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/create-recruitment-news")
    public ResponseEntity<RecruitmentNews> saveRecruitmentNews(@RequestBody RecruitmentNews recruitmentNews) {
        return new ResponseEntity<>(recruitmentNewsService.save(recruitmentNews), HttpStatus.OK);
    }
}
