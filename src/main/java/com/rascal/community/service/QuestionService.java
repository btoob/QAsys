package com.rascal.community.service;

import com.rascal.community.Mapper.QuestionMapper;
import com.rascal.community.Mapper.UserMapper;
import com.rascal.community.Model.Question;
import com.rascal.community.Model.User;
import com.rascal.community.dto.QuestionDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;

    public List<QuestionDTO> getAllQuestion() {
        List<Question> allQuestion = questionMapper.getAllQuestion();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : allQuestion) {
            User user = userMapper.getById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
