package com.rafael.userschema.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rafael.userschema.model.UserSchema;

public interface UserSchemaRepository
		extends PagingAndSortingRepository<UserSchema, String> {

}
