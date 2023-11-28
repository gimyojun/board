package com.ll.board.domain.home.home.controller;

import com.ll.board.global.rq.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmHomeController {

    private final Rq rq;
    @GetMapping("")
    public String showMain() {
        return "home/home/adm/main";
    }

    @GetMapping("/home/about")
    public String showAbout() {
        return "home/home/adm/about";
    }

}
