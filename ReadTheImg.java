package AI_Machine_Learning.google_api;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

public abstract class ReadTheImg {
	
	public void analysisPicture(String inputDir, String outputDir)  throws Exception {
		
		  ImageAnnotatorClient vision = ImageAnnotatorClient.create(); // Google client obj
		  List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();
		  // Google Client Request //
		  
		  File input = new File(inputDir);
		  String[] inputFileNames = input.list();      // input.list() = file's name
		  for(String inputName: inputFileNames) {
			  String fileName = inputDir + "/" + inputName;
			  
			  Path path = Paths.get(fileName);         // Read the path by the fileName 
			  byte[] data = Files.readAllBytes(path);  // Read the data by the path
			  ByteString imgBytes = ByteString.copyFrom(data); // Create ByteString object
			  
			  Image img = Image.newBuilder().setContent(imgBytes).build();
			  Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build(); // label like description and score
			  
			  AnnotateImageRequest request = AnnotateImageRequest.newBuilder()  // Create request (for run)
					  .addFeatures(feat)
					  .setImage(img)
					  .build();
			  requests.add(request);  // Throw to ArrayList at row 32
		  }
		  
		  List<List<String[]>> dataList = new ArrayList<List<String[]>>();
		  BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests); // Call google API
		  List<AnnotateImageResponse> responses = response.getResponsesList();         // Throw response to response list
		  
		  for(AnnotateImageResponse res : responses) {
			 /* if(res.hasError()) {
				  System.out.println(res.getError().getMessage());
				  return;
			  }*/
			  List<String[]> datas = new ArrayList<String[]>();
			  
			  for(EntityAnnotation annotation : res.getLabelAnnotationsList()) {
				  String[] data = new String[2];
				  data[0] = annotation.getDescription();
				  data[1] = String.valueOf(annotation.getScore());
				  
				  datas.add(data);
			  }
			  dataList.add(datas);
		  }
		  writeData(outputDir, inputFileNames, dataList);
  	   
	}
	public abstract void writeData(String outDir, String[] inputFileNames, List<List<String[]>> dataList);
  }

	
