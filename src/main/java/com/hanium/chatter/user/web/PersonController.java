package com.hanium.chatter.user.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hanium.chatter.user.domain.Person;
import com.hanium.chatter.user.domain.PersonRepository;

@Controller
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@PostMapping("/registration")
	public ModelAndView addNewUser(@Valid Person user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		personRepository.save(user);
		modelAndView.addObject("user", new Person());
        modelAndView.setViewName("signin");
		return modelAndView;
	}
	
	@GetMapping("/all")
	public @ResponseBody Iterable<Person> getAllUsers() {
		return personRepository.findAll();
	}
}
