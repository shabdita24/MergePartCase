package com.mergepc.service;
//package com.egovernance.mergeImpl;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Service;
//
//import io.minio.BucketExistsArgs;
//import io.minio.GetObjectArgs;
//import io.minio.MinioClient;
//import io.minio.errors.MinioException;
//
//@Service
//public class MinioTasks {
//	Logger log=Logger.getLogger(MinioTasks.class);
//
//	
//	public String checkBucketExist(String url, String user, String pass) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
//		log.info(url+"\t"+user+"\t"+pass);
//		 try {
//		      MinioClient minioClient =
//		          MinioClient.builder()
//		              .endpoint("http://127.0.0.1:9000")
//		              .credentials("admin","admin12345")
//		              .build();
//
//		      boolean found =
//		          minioClient.bucketExists(BucketExistsArgs.builder().bucket("bucketa").build());
//		      if (!found) {
//		    	   log.info("Bucket 'bucketa' not exists.");	
//		    	   return "Bucket 'bucketa' not exists.";
//		    	   } else {
//		      log.info("Bucket 'bucketa' already exists.");
//		      return "Bucket 'bucketa' already exists.";
//		      }
//
//		}
//		catch(MinioException e) {
//			log.warn("line 4");
//
//			log.warn("Exception in minio "+e.toString());
//			log.warn("line 5");
//
//		}
//		return "error";
//		
//	}
//	  public  ByteArrayOutputStream copyfile(String url, String miniourl, String user, String pass)
//		      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//		  log.info("*********MinioTask*********");
//			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
//
//		    try {
//		    	String bucketname="";
//		    	String objectPath="";
//		    	String[] s=url.split("/");
//		    	bucketname=s[3];
//		    	for(int i=4;i<s.length;i++) {
//		    		if(i==s.length-1)
//		    			objectPath+=s[i];
//		    		else
//		    		objectPath+=s[i]+"/";
//		    	}
//		    	log.warn("bucketname "+bucketname);
//		    	log.warn("objectPath "+objectPath);
//
//		      MinioClient minioClient =
//		          MinioClient.builder()
//		              .endpoint(miniourl)
//		              .credentials(user,pass)
//		              .build();
//		    	try (InputStream stream = minioClient.getObject(
//		    			  GetObjectArgs.builder()
//		    			  .bucket(bucketname)
//		    			  .object(objectPath)
//		    			  .build())) {		    		
//					byte[] buff = new byte[8000];
//					int bytesRead = 0;
//					while((bytesRead = stream.read(buff)) != -1) {
//						bytearrayoutputstream.write(buff, 0, bytesRead);
//					}
//		    		}
//		    	
//				return bytearrayoutputstream;
//
//		    } catch (MinioException e) {
//		     log.warn("Error occurred: " + e);
//		     // log.warn("HTTP trace: " + e.httpTrace());
//		    }
//			return bytearrayoutputstream;
//	
//	  }
//
//}
