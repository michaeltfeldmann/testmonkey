package aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 2/5/2015.
 */
public class LearnAWS_ReadInCredentials {

    public static void main(String[] args) throws IOException {

        //check where you think your home directory is
        // Directory path here
        String path = ".";
        String awsKeyPath = "C:\\Users\\Michael\\.aws";

        String files;
        File folder = new File(awsKeyPath);
        System.out.println("folder's full path: " + folder.getAbsolutePath());
        System.out.println("folder's can. path: " + folder.getCanonicalPath());
        File[] listOfFiles = folder.listFiles();

        System.out.println("# of files in this path: "+listOfFiles.length);

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                System.out.println(files);
            }
        }

        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (~/.aws/credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        //check what is in the credentials object
        System.out.println(credentials);

        AmazonS3 s3 = new AmazonS3Client(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        s3.setRegion(usWest2);
    }
}
