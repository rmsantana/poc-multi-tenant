package com.rafael.userschema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.userschema.service.UserSchemaService;

@RestController
@RequestMapping("/user-schemas")
public class UserSchemaController {

	@Autowired
	private UserSchemaService userSchemaService;

	@RequestMapping(method = RequestMethod.GET, value = "{schemaName}")
	public void save(@PathVariable("schemaName") String schemaName) {
		userSchemaService.save(schemaName);
	}
}
