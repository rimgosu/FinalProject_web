import java.io.File;
import java.nio.file.Paths;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import software.amazon.awssdk.services.s3.S3Client;
import com.amazonaws.services.s3.model.Bucket;


public class aws_test {
	    public static void main(String[] args) {
	        final String USAGE = "\n" +
	                "To run this example, supply the name of an S3 bucket and a file to\n" +
	                "upload to it.\n" +
	                "\n" +
	                "Ex: PutObject <bucketname> <filename>\n";

	        if (args.length < 2) {
	            System.out.println(USAGE);
	            System.exit(1);
	        }

	        String bucket_name = args[0];
	        String file_path = args[1];
	        String key_name = Paths.get(file_path).getFileName().toString();

	        System.out.format("Uploading %s to S3 bucket %s...\n", file_path, bucket_name);
	        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
	        try {
	            s3.putObject(bucket_name, key_name, new File(file_path));
	        } catch (AmazonServiceException e) {
	            System.err.println(e.getErrorMessage());
	            System.exit(1);
	        }
	        System.out.println("Done!");
	    }

}
