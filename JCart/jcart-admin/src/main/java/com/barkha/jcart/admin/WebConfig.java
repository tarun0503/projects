package com.barkha.jcart.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("public/login");
		registry.addRedirectViewController("/", "/home");
	}
}
