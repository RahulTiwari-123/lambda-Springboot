package com.lambda.controller;

import com.amazonaws.services.dynamodbv2.document.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.lambda.dto.BusinessDto;

import java.util.UUID;

@EnableWebMvc
@RestController
public class MainController {

	@Autowired
	DynamoService dbService;

	@RequestMapping(path = "/ping", method = RequestMethod.GET)
	public ResponseEntity<String> ping() {
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}

	@RequestMapping(path = "/schedule/{id}", method = RequestMethod.GET)
	public BusinessDto path2(@PathVariable String id) {
		String message = "Name " + id;
		BusinessDto dto = new BusinessDto();
		dto.setMessage(message);
		return dto;
	}


	@RequestMapping(path = "/schedule", method = RequestMethod.POST)
	public BusinessDto path3(@RequestBody String id) {
		String message = "Name " + id;
		BusinessDto dto = new BusinessDto();
		dto.setMessage(message);
		Item item = new Item().withPrimaryKey("id",UUID.randomUUID().toString()).withString("value",id);
		dbService.getTable().putItem(item);
		return dto;
	}
}
