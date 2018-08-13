package com.molly.forecasting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.molly.forecasting.entity.User;
import com.molly.forecasting.service.UserHistoryService;
import com.molly.forecasting.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserHistoryService userHistoryService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		
		modelAndView.addObject("userName",
				user.getName() + " " + user.getLastName() );
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView("login/index");
		userHistoryService.getAllUsersHistory().forEach(item -> {
			System.out.println(item);
		});
		return modelAndView;
	}
}
