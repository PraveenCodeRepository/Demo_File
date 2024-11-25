package com.praveen.Demo_File.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.praveen.Demo_File.dto.FileDto;
import com.praveen.Demo_File.entity.File;
import com.praveen.Demo_File.repository.FileRepository;
import com.praveen.Demo_File.util.FileUtil;


@Service
public class FileService {
	
	private final FileRepository fileRepository;
	
	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}
	
	
	  public FileDto saveFileInDb(FileDto fileDto) throws IOException {
	  
	  int dataSizeBefore = fileDto.getData().length;
	  System.out.println("Before Compress Data size ======= "+dataSizeBefore);
	  
	  File file = File.builder()
			  .name(fileDto.getName())
			  .type(fileDto.getType())
	          .data(FileUtil.compressData(fileDto.getData()))
	          .build();
	  
	  File fileSaved = fileRepository.save(file); 
	  LocalDateTime createdAt = fileSaved.getCreatedAt();
	  System.out.println("File created at ====== "+createdAt);
	  
	  LocalDateTime updatedAt = fileSaved.getUpdatedAt();
	  System.out.println("File updated at ====== "+updatedAt);
	  
	  int dataSizeAfter = fileSaved.getData().length;
	  System.out.println("After Compress Data size ====== "+dataSizeAfter);
	  
	  FileDto fileDtoResponse = FileDto.builder()
			  .name(fileSaved.getName())
	         .type(fileSaved.getType())
	         .data(fileSaved.getData())
	         .id(fileSaved.getId())
	        .createdAt(fileSaved.getCreatedAt()) .updatedAt(fileSaved.getUpdatedAt())
	        .build();
	  
	  return fileDtoResponse;
	  
	  
	  }
	  
	  public FileDto getFileById(Integer id) throws IOException {
	  
	  Optional<File> file =fileRepository.findById(id);
	  
	  if(file.isPresent()) {
	  
	  File fileFound = file.get();
	  
	  int before = fileFound.getData().length;
	  System.out.println(" Compress data size ====== " + before);
	  
	  
	  FileDto fileDtoResponse = FileDto.builder() .id(fileFound.getId())
	  .name(fileFound.getName()) .type(fileFound.getType())
	  .createdAt(fileFound.getCreatedAt()) .updatedAt(fileFound.getUpdatedAt())
	  .data(FileUtil.decompressData(fileFound.getData())) .build();
	  
	  int after = fileDtoResponse.getData().length;
	  System.out.println("Decompress data size ====== " + after);
	  
	  return fileDtoResponse;
	  }
	  
	  else throw new RuntimeException("File not found");
	  }
	 	
	
	
	
	 
}


