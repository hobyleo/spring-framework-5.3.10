package com.hoby.xml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hoby
 * @since 2024-01-12
 */
@Controller
@RequestMapping("/request")
public class RequestMappingController {

	@RequestMapping("/mapping")
	public ModelAndView mapping() {
		System.out.println("RequestMappingController Working");
		ModelAndView modelAndView = new ModelAndView("a");
		modelAndView.addObject("source", "RequestMappingController");
		return modelAndView;
	}

	@RequestMapping(value = "/mappin*")
	@ResponseBody
	public String mapping02() {
		// * 匹配零到多个字符
		return "通配符: *";
	}

	@RequestMapping(value = "/mappin?")
	@ResponseBody
	public String mapping03() {
		// ? 匹配一个字符
		return "通配符: ?";
	}

	@RequestMapping(value = "/**")
	@ResponseBody
	public String mapping04() {
		// 当上面的所有映射都未匹配上时，才会走/**
		return "通配符: /**";
	}

}
