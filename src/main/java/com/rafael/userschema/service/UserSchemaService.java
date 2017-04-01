package com.rafael.userschema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.liquibase.service.LiquibaseService;
import com.rafael.userschema.model.UserSchema;
import com.rafael.userschema.repository.UserSchemaRepository;

@Service
public class UserSchemaService {

	@Autowired
	private UserSchemaRepository userSchemaRepository;

	@Autowired
	private LiquibaseService liquibaseService;

	public void save(String schemaName) {
		UserSchema userSchema = new UserSchema();
		userSchema.setSchemaName(schemaName);
		userSchemaRepository.save(userSchema);
		liquibaseService.run();
	}
}
