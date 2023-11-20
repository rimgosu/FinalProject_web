package kr.spring.config;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 s3client() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("c:/keys/s3/s3-keys.json");
            Map<String, String> awsKeys = objectMapper.readValue(file, Map.class);

            String accessKey = awsKeys.get("accessKeyId");
            String secretKey = awsKeys.get("secretAccessKey");
            String region = awsKeys.get("region");

            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion(Regions.fromName(region))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error loading AWS credentials from file", e);
        }
    }
}