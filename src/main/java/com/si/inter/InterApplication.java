package com.si.inter;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterApplication.class, args);
	}

	@PostConstruct
	public void start() {
		FilesystemService  filesystemService =  FilesystemService.getINSTANCE();

		String file1 = new String("file1");
		String file2 = new String("file2");
		filesystemService.fcreate(file1);
		filesystemService.fwrite(file1, "Hello File 1");
//		System.out.println(filesystemService.fread(file1));

		filesystemService.fcreate(file2);
		filesystemService.fwrite(file2, "Hello File 2");
//		System.out.println(filesystemService.fread(file2));

		filesystemService.fwrite(file1, "Hello Again File 1");
//		System.out.println(filesystemService.fread(file1));


	}
}
