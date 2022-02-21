package com.bernardguiang.test.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FormController {
	
	@PostMapping(path = "/test", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> testSubmit(
			@RequestPart String address, 
			@RequestPart MultipartFile[] files) throws IOException {
		System.out.println("Address: " + address);
		System.out.println("Saving File....");
		
		final String uploadsPath = "src/uploads/"; //path
		List<String> fileNames = new ArrayList<>();
	      Arrays.asList(files).stream().forEach(file -> {
	    	  try {
		        FileOutputStream output = new FileOutputStream(uploadsPath+file.getOriginalFilename());
		        output.write(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        fileNames.add(file.getOriginalFilename());
	      });
	      String message = "Uploaded the files successfully: " + fileNames;
	      System.out.println(message);
	    return ResponseEntity.ok().build();
	}
	
}
