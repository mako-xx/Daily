package com.travelthree.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 陈宣辰
 * @date 2022-11-27 17:05:05
 * @description
 */
@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/test")
    public String test() {
        return "index";
    }
}
