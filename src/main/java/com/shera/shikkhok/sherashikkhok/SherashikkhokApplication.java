package com.shera.shikkhok.sherashikkhok;


import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shera.shikkhok.sherashikkhok.service.impl.StorageServiceImpl;

@SpringBootApplication
public class SherashikkhokApplication implements CommandLineRunner {
	
	@Resource
	StorageServiceImpl storageServiceimpl;
	
	

	public static void main(String[] args) {
		SpringApplication.run(SherashikkhokApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		storageServiceimpl.deleteAll();
		storageServiceimpl.init();
	}
}
