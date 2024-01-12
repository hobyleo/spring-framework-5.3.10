package com.hoby.xml.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hoby
 * @since 2024-01-12
 */
@Component
public class SimpleController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SimpleController Working");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "SimpleController");
		return modelAndView;
	}

}
