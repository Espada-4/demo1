package com.mark.demo.controller;

import com.mark.demo.cache.TafCache;
import com.mark.demo.dto.QuestionDTO;
import com.mark.demo.model.Question;
import com.mark.demo.model.User;
import com.mark.demo.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model,
                       HttpServletRequest request) {
        QuestionDTO question = questionService.getById(id);
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() != question.getCreator()) {
            return "redirect:/";
        }


        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TafCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TafCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Long id,
            HttpServletRequest request,
            Model model) {


        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title.isEmpty()) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description.isEmpty()) {
            model.addAttribute("error", "补充不能为空");
            return "publish";
        }
        if (tag == null || tag.isEmpty()) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        String invalid = TafCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
            return "publish";
        }


        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        question.setCreator(user.getId());
        questionService.createOrUpdateQuestion(question);
        return "redirect:/";
    }
}
