package com.praveen.Demo_File.entity.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.praveen.Demo_File.dto.FileDto;
import com.praveen.Demo_File.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {
	
	private final FileService fileService;
	
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<FileDto> uploadFile(@RequestParam("file") MultipartFile file ){
		 try {
			 
			 String fileName = file.getOriginalFilename();
			 System.out.println("File Name ========= "+ fileName);
			 
			 String fileType = file.getContentType();
			 System.out.println("File Type ========= "+ fileType);
			 
			 byte[] fileData = file.getBytes();
			 System.out.println("File Data ========= "+ fileData);
			 
			FileDto fileDto = FileDto.builder()
			                         .name(fileName)
			                         .type(fileType)
			                         .data(fileData)
			                         .build();
			
			 FileDto fileDtoSaved =fileService.saveFile(fileDto);
			 
			 return new ResponseEntity<FileDto>(fileDtoSaved,HttpStatus.CREATED);
			 
		 }catch(Exception e) {
			 
			 return new ResponseEntity<FileDto>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		 }
		 
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id){
		 
		  try {
			  
			 FileDto fileDtoFound = fileService.getFileById(id);
			 
			String fileName = fileDtoFound.getName();
			String contentType = fileDtoFound.getType();
			byte[] data = fileDtoFound.getData();
			 
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.parseMediaType(contentType));
			headers.setContentDispositionFormData("attachment", fileName);
			
			return new ResponseEntity<>(data , headers , HttpStatus.OK);
			
		  }catch(Exception e) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  }
		  
		  
		
	}

}
