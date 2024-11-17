package com.mawulidev.oauthapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/")
    public String getLoginPage(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            System.out.println("OAuth2User attributes: " + principal.getAttributes());
            model.addAttribute("email", principal.getAttribute("email"));     // Add the profile picture URL (if available)
            model.addAttribute("authorities", principal.getAuthorities());    // Add the authorities
        }
        return "oauth_login";
    }
}
