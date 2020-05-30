package AI_Machine_Learning.google_api;

public class RunGoogleAnalysis {
	public static void main(String[] args) throws Exception {
		
		String inputDir = "/Users/lochieh/Desktop/AI & Machine Learning Products/input";
		String outputDir = "/Users/lochieh/Desktop/AI & Machine Learning Products/output";
	
		ReadTheImg Read = new TxtHelp();
		Read.analysisPicture(inputDir, outputDir);
	}

}
