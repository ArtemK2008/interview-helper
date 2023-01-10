package com.project.green.controller.mvc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class LoginController {


    @GetMapping("/index")
    public String showSignUpForm() {
        return "login/index";
    }

    @PostMapping("/index")
    public RedirectView handleLogin(@RequestParam("idFromIndex") String personId, HttpServletRequest request) {
        request.getSession().setAttribute("sessionId", personId);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/person/" + personId);
        return redirectView;
    }

    @GetMapping("/person/{personId}")
    public String personPage(@PathVariable("personId") String personId, Model model) {
        model.addAttribute("id", personId);
        return "login/personPage";

    }


}
