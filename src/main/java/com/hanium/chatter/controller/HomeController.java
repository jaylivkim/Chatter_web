package com.hanium.chatter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
	
	@GetMapping("/chat")
	public ModelAndView chat(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chatting");
        return modelAndView;
    }
	
	@GetMapping("/userLogin")
	public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }
	
	@GetMapping("/registration")
	public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }
	
	@GetMapping("/lobby")
	public ModelAndView lobby(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mypage");
        return modelAndView;
    }
}
