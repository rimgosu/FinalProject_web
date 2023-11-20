package kr.spring;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;




@SpringBootTest
class PracticeApplicationTests {
	
	@Autowired
	private AmazonS3 s3client;

	@Test
	void contextLoads() {
		File file = new File("C:/Users/gjaischool/Desktop/이미지/1.jpg");
		String bucketName = "simkoong-s3";
		String fileName="1.jpg";
		s3client.putObject(new PutObjectRequest(bucketName, fileName, file));
	}

}
