package bid.reader.general;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import bid.analyzer.beans.CV;
import bid.reader.linkedin.LinkedinReader;

public class CVDispatcher {
	

	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(CVDispatcher.class);
	
	
	private LinkedinReader linkedin;
	private final String linkedinSign = "".toLowerCase();
	
	public CVDispatcher() {
		linkedin = new LinkedinReader();
	}
	
	public String read(File f) {
		String ret = "";
		try {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			PDDocument document = PDDocument.load(f);
			ret = pdfStripper.getText(document);
			ret = cleanse(ret);
			document.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private String cleanse(String str) {
		String ret = str;
		ret = ret.replaceAll("\"", "");
		ret = ret.replaceAll("'", "");
		return ret;
	}
	
	
	public CV getCV(File f) {
		String text = read(f);
		if(text.toLowerCase().contains(linkedinSign)) {
			return linkedin.buildCV(text);
		}
		return null;
	}
	
	
	
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("props/log4j.properties");
		
		File f = new File("D:\\BID\\Interno\\Progetti\\CV Analytics\\cvs\\Gianluca_Meloni.pdf");
		CVDispatcher disp = new CVDispatcher();
		//System.out.println(disp.read(f));
		
		CV cv = disp.getCV(f);
		System.out.println(cv.toCSV());
		
		
	}
	
	
	
	

}
