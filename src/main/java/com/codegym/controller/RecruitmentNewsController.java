package com.codegym.controller;

import com.codegym.constant.Constant;
import com.codegym.model.dto.response.ResponMessage;
import com.codegym.model.entity.Company;
import com.codegym.model.entity.RecruitmentNews;
import com.codegym.service.company.CompanyService;
import com.codegym.service.recruitment_news.IRecruitmentNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.codegym.constant.Constant.Status.Khóa;
import static com.codegym.constant.Constant.Status.Mở;


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
            if (recruitmentNewsOptional.get().getStatus().equals(Khóa)) {
                recruitmentNewsOptional.get().setStatus(Mở);
            } else recruitmentNewsOptional.get().setStatus(Khóa);
        }
        return new ResponseEntity<>(recruitmentNewsService.save(recruitmentNewsOptional.get()), HttpStatus.OK);
    }

    //set tin tuyen dung la de xuat
    @PutMapping("/set-propose/{id}")
    public ResponseEntity<RecruitmentNews> setProposal(@PathVariable Long id) {
        Optional<RecruitmentNews> recruitmentNews = recruitmentNewsService.findById(id);
        if (!recruitmentNews.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (recruitmentNews.get().getProposed().equals(Constant.Proposal.Không)) {
                recruitmentNews.get().setProposed(Constant.Proposal.Có);
            } else recruitmentNews.get().setProposed(Constant.Proposal.Không);
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
    public ResponseEntity<?> saveRecruitmentNews(@RequestBody RecruitmentNews recruitmentNews) {
        if (recruitmentNews.getTitle() == "") {
            return new ResponseEntity<>(new ResponMessage("Hãy điền tên tiêu đề!!"), HttpStatus.OK);
        }
        if (recruitmentNews.getField().getId() == null) {
            return new ResponseEntity<>(new ResponMessage("Hãy điền tên lĩnh vực đang làm!!"), HttpStatus.OK);
        }
        if (recruitmentNews.getWorkingPlace() == "") {
            return new ResponseEntity<>(new ResponMessage("Hãy điền nơi làm việc!!"), HttpStatus.OK);
        }
        if (recruitmentNews.getSalaryFrom() == null) {
            return new ResponseEntity<>(new ResponMessage("Hãy điền mức lương khởi điểm!!"), HttpStatus.OK);
        }
        recruitmentNews.setProposed(Constant.Proposal.Không);
        recruitmentNews.setStatus(Mở);
        recruitmentNewsService.save(recruitmentNews);
        return new ResponseEntity<>(new ResponMessage("Thêm tim tuyển dụng thành công!!"), HttpStatus.OK);
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
//    @GetMapping("/unlocked-list")
//    public ResponseEntity<Iterable<RecruitmentNews>> findAllUnlockRecruitmentNews() {
//        return new ResponseEntity<>(recruitmentNewsService.findAllUnlockRecruitmentNews(), HttpStatus.OK);
//    }
    @GetMapping("/unlocked-list")
    public ResponseEntity<Page<RecruitmentNews>> findAllUnlockRecruitmentNews(Pageable pageable) {
        return new ResponseEntity<>(recruitmentNewsService.findUnlockRecruitmentNews(pageable), HttpStatus.OK);
    }

    //tim kiem nhanh theo tieu de,linh vuc, noi lam viec, luong nho nhat
    @GetMapping("/search")
    public ResponseEntity<Iterable<RecruitmentNews>> searchAllRecruitmentNews(@RequestParam("title") String title) {
        Iterable<RecruitmentNews> recruitmentNews=recruitmentNewsService.findAllRecruitmentNews(title);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }
    //tim kiem nhanh theo ten noi lam viec
    @GetMapping("/search-by-working-place")
    public ResponseEntity<Iterable<RecruitmentNews>> searchByWorkingPlace(@RequestParam("title") String title) {
        Iterable<RecruitmentNews> recruitmentNews=recruitmentNewsService.findAllByWorkingPlace(title);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }
    //tim kiem nhanh theo ten chuyen nganh
    @GetMapping("/search-by-field-name")
    public ResponseEntity<Iterable<RecruitmentNews>> searchByFieldName(@RequestParam("title") String title) {
        Iterable<RecruitmentNews> recruitmentNews=recruitmentNewsService.findRecruimentByFieldName(title);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }
    //tim kiem nhanh theo tieu de,ten cong ty
    @GetMapping("/search-by-title-companyname")
    public ResponseEntity<Iterable<RecruitmentNews>> findRecruimentByTitleAndCompanyName(@RequestParam("title") String title) {
        Iterable<RecruitmentNews> recruitmentNews=recruitmentNewsService.findRecruimentByTitleAndCompanyName(title);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }

    //tim kiem nhanh theo ten nganh nghe, noi lam viec
    @GetMapping("/search-by-field-working-place")
    public ResponseEntity<Iterable<RecruitmentNews>> findRecruimentByFieldAndWorkingPlace(@RequestParam("title") String title) {
        Iterable<RecruitmentNews> recruitmentNews=recruitmentNewsService.findRecruimentByFieldAndWorkingPlace(title);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }

    //tim theo ten tieu de ,kinh nghiem,thanh pho
    @GetMapping("/search-by-title-working-place-experience")
    public ResponseEntity<Iterable<RecruitmentNews>> findRecruitmentNewsByTitleWorkingPlaceExperience(@RequestParam("title") String title) {
        Iterable<RecruitmentNews> recruitmentNews=recruitmentNewsService.findRecruitmentNewsByTitleWorkingPlaceExperience(title);
        return new ResponseEntity<>(recruitmentNews, HttpStatus.OK);
    }
}
