package com.rascal.community.controller;

import com.rascal.community.dto.QuestionDTO;
import com.rascal.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id, Model model) {
        Integer questionId = null;
        try {
            questionId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        QuestionDTO questionDTO = questionService.getById(questionId);
        model.addAttribute("question", questionDTO);

        return "question";
    }
}
