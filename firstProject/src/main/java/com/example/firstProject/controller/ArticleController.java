package com.example.firstProject.controller;

import com.example.firstProject.DTO.ArticleForm;
import com.example.firstProject.entity.Article;
import com.example.firstProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {
    @GetMapping("/Article/new")
    public String Articleform() {
        return "Article/new";
    }

    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/Article/create")
    public String ArticleCreate(ArticleForm form) {

        log.info(form.toString());

        Article article = form.toEntity();
        log.info(article.toString());

        Article saved =articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }
}
