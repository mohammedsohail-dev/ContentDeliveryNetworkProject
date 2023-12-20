package com.Cdncontroller.cdncontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class Cdncontroller {

	@GetMapping("/videos/{fileName}")
	 public ResponseEntity<Void> redirect(@PathVariable(value = "fileName") String fileName){
		
		Random random = new Random();
		int number = random.nextInt(5)+1;
	 
	 switch(number) {  
	 case 1:    
	   return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://localhost:8081/api/videos/"+fileName)).build();
	 case 2:
	   return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://localhost:8082/api/videos/"+fileName)).build(); 
	 case 3:
		   return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://localhost:8083/api/videos/"+fileName)).build(); 
	 case 4:
		   return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://localhost:8084/api/videos/"+fileName)).build(); 
	 case 5:
		   return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://localhost:8085/api/videos/"+fileName)).build(); 
	 default:
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
	 
	 } 
	}
}
