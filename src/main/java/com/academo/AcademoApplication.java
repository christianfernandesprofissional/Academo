package com.academo;

import com.academo.util.FileTransfer.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
public class AcademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademoApplication.class, args);
	}

}
