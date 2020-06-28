package com.test.docker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker")
public class DockerController {

	
	@GetMapping("/test/{name}")
	public ResponseEntity<Object> testDocker(@PathVariable(value="name", required=true) String nameId) {
				
			
	return new ResponseEntity<>("Welcome to Docker    "+ nameId,HttpStatus.OK);
	}
}
