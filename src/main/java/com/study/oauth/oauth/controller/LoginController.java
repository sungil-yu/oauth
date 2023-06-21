package com.study.oauth.oauth.controller;

import com.study.oauth.oauth.service.CustomOAuth2AuthorizedClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final CustomOAuth2AuthorizedClientService customOAuth2AuthorizedClientService;

    @GetMapping("/login")
    public String loginRequest() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model, OAuth2AuthenticationToken authentication) {

        System.out.println("authentication = " + authentication);

        model.addAttribute("userName", authentication.getName());



        return "main";
    }

    @PostMapping("/login")
    public String loginResponse() {
        return "main";
    }



}
