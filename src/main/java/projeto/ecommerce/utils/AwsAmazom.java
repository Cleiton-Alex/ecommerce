package projeto.ecommerce.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class AwsAmazom {
    AWSCredentials credentials = new BasicAWSCredentials(
            "OODPGBVAOGPUEBJITYDK",
            "f+0Y/PGDfO2WndLFCpMNQeSGmthHWEhr6lM63DwwvOI"
    );

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.DEFAULT_REGION)
            .build();

    public void upload(String path, File file){
        s3client.putObject(
                "promitech",
                path,
                file
        );
    }
}
