package com.mark.demo.controller;

import com.mark.demo.dto.AccessTokenDTO;
import com.mark.demo.dto.GithubUser;
import com.mark.demo.model.User;
import com.mark.demo.provider.GithubProvider;
import com.mark.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AutherizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client_id}")
    private String clientID;
    @Value("${github.client_secret}")
    private String client_secret;
    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId()!=null) {
            //登录成功写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
