package com.rascal.community.controller;

import com.rascal.community.Mapper.QuestionMapper;
import com.rascal.community.Model.Question;
import com.rascal.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam String title,
                            @RequestParam String description,
                            @RequestParam String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if ("".equals(title)){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if ("".equals(description)){
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if ("".equals(tag)){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user==null) {
            model.addAttribute("error", "用户未登陆");
            return "publish";
        }
        Question question = new Question();
        question.setDescription(description);
        question.setTitle(title);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        assert user != null;
        question.setCreator(user.getId());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
