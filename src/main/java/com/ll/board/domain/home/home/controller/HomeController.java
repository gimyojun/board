package com.ll.board.domain.home.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    //@ResponseBody
    String showHome(){

        return "redirect:/article/list";
    }


}
