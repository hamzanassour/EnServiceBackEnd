package com.example.enserviceback.utils;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Date;

public class SignedUrl {

  public static URL generateSignedUrl(String bucketName , String ObjectKey , AmazonS3 amazonS3){
       Date expiration = new Date();
       long expTimeMillis = expiration.getTime();
       expTimeMillis += 1000 * 60 * 60; // Add 1 hour.
       expiration.setTime(expTimeMillis);

       // Generate the presigned URL.
       GeneratePresignedUrlRequest generatePresignedUrlRequest =
               new GeneratePresignedUrlRequest(bucketName, ObjectKey)
                       .withMethod(HttpMethod.GET)
                       .withExpiration(expiration);

       return  amazonS3.generatePresignedUrl(generatePresignedUrlRequest) ;
  }

}
