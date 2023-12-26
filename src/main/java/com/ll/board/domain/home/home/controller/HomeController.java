package com.ll.board.domain.home.home.controller;

import com.ll.board.global.rq.Rq;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final Rq rq;
    @GetMapping("/")
    //@ResponseBody
    public String showHome(String msg){

        return rq.redirect("/article/list", msg);
    }
    @GetMapping("/home/session")
    @ResponseBody
    public Map<String, Object> showSession(HttpSession session){
        return Collections.list(session.getAttributeNames())
                .stream()
                .collect(
                        Collectors.toMap(
                                key -> key,
                                key -> session.getAttribute(key)
                            )
                        );
    }


}
