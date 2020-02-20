package com.mark.demo.controller;

import com.mark.demo.dto.AccessTokenDTO;
import com.mark.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutherizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("Iv1.f81e011ba895fb36");
        accessTokenDTO.setClient_secret("318ba0aa387a5666ba70d425475f25987aa61042");
        githubProvider.getAccessToken(accessTokenDTO);
        System.out.println();
        return "index";
    }
}
