package kr.spring.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import com.amazonaws.util.IOUtils;

@Controller
public class FileController {
	
	@Autowired
    private AmazonS3 s3client; // 초기화 필요

    @RequestMapping("/readFile") // 이미지 불러올때.
    public String readFileFromS3(Model model) {
    	try {
    		String bucketName = "simkoong-s3";
            String fileName = "1.jpg";

            S3Object s3object = s3client.getObject(bucketName, fileName);
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            
            byte[] bytes = IOUtils.toByteArray(inputStream);
            String base64Encoded = Base64.encodeBase64String(bytes);

            model.addAttribute("imageData", base64Encoded);
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
        
        

     
        return "test"; // JSP 파일 이름
    }
}