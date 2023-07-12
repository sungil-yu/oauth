package com.study.oauth.oauth.controller;

import com.study.oauth.oauth.service.CustomOAuth2AuthorizedClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class LoginController {

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
<<<<<<< Updated upstream



        OAuth2User principal = authentication.getPrincipal();


        authentication.getAuthorities().forEach(System.out::println);

        System.out.println("--------------------");
        System.out.println(principal.getAttributes());


        model.addAttribute("userName", principal.getAttribute("name"));
        model.addAttribute("userEmail", principal.getAttributes().get("email"));
        model.addAttribute("userImageUrl", principal.getAttributes().get("picture"));




=======
        model.addAttribute("userName", authentication.getName());
>>>>>>> Stashed changes
        return "main";
    }

    @PostMapping("/login")
    public String loginResponse() {
        return "main";
    }



}
