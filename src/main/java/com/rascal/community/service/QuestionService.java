package com.rascal.community.service;

import com.rascal.community.Mapper.QuestionMapper;
import com.rascal.community.Mapper.UserMapper;
import com.rascal.community.Model.Question;
import com.rascal.community.Model.User;
import com.rascal.community.dto.PaginationDTO;
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

    public PaginationDTO getAllQuestion(Integer page, Integer size) {
        Integer offset = size*(page-1);     //每页起始数据的索引

        List<Question> allQuestion = questionMapper.getAllQuestion(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        for (Question question : allQuestion) {
            User user = userMapper.getById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        Integer totalCount = questionMapper.count();
        int totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);
        return paginationDTO;
    }
}
