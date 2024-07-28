package com.si.inter;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterApplication {

	/**
	 * The entry point of the InterApplication.
	 * 
	 * This method initializes the Spring application context and starts the application.
	 * 
	 * @param args command-line arguments passed to the application. These arguments can be used to configure the application at startup.
	 * 
	 * @throws Exception if the application context cannot be started or if there are issues during the initialization of the Spring application.
	 */
	public static void main(String[] args) {
		
		SpringApplication.run(InterApplication.class, args);
	}

	@PostConstruct
	/**
	 * Starts the file operations by creating and writing to two files.
	 * <p>
	 * This method utilizes the {@link FilesystemService} to create two files named "file1" and "file2".
	 * It writes a string to each file and then writes an additional string to "file1".
	 * </p>
	 * <p>
	 * The method does not return any value and does not handle exceptions internally. 
	 * It is expected that the {@link FilesystemService} methods may throw exceptions 
	 * if there are issues with file creation or writing.
	 * </p>
	 *
	 * @throws IOException if an I/O error occurs during file creation or writing.
	 * @throws FileNotFoundException if the specified file cannot be created or accessed.
	 */
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
