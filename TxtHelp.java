package AI_Machine_Learning.google_api;

import java.io.*;
import java.util.*;

public class TxtHelp extends ReadTheImg {
	@Override
	public void writeData(String outDir, String[] inputFileNames, List<List<String[]>> dataList) {
		try {
			for(int i = 0; i < inputFileNames.length; i++) {
				 PrintWriter writer = new PrintWriter(outDir + "//" + inputFileNames[i] + ".txt","UTF-8");
				 List<String[]> datas = dataList.get(i);
				 
				 for(int j = 0; j < datas.size(); j++) {
					 String[] rowData = datas.get(j);
					 writer.println(rowData[0] + "  " + rowData[1]);
				 }
				  writer.close();
			}
		}catch(IOException e) {
			System.out.println("Can not be create");
		}
	}
}
