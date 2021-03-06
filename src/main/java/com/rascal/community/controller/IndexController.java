package com.rascal.community.controller;

import com.rascal.community.Mapper.UserMapper;
import com.rascal.community.dto.PaginationDTO;
import com.rascal.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size) {

        PaginationDTO pagination = questionService.getAllQuestion(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
