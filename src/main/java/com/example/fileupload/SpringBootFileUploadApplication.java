package com.example.fileupload;


import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.example.fileupload.service.FilesStorageService;


@SpringBootApplication
public class SpringBootFileUploadApplication implements CommandLineRunner {


@Resource
FilesStorageService storageService;


public static void main(String[] args) {
SpringApplication.run(SpringBootFileUploadApplication.class, args);
}


@Override
public void run(String... args) throws Exception {
// Uncomment the next line to clear uploads on each start (not recommended in production)
// storageService.deleteAll();
storageService.init();
}
}