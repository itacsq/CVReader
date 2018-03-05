package bid.reader.linkedin;

import org.apache.log4j.Logger;


import bid.analyzer.beans.CV;
import bid.reader.cv.CVReader;



/**
 *  ##\       ##\       ##\ 
 *  ## |      \__|      ## |
 *  #######\  ##\  ####### |
 *  ##  __##\ ## |##  __## |
 *  ## |  ## |## |## /  ## |
 *  ## |  ## |## |## |  ## |
 *  #######  |## |\####### |
 *  \_______/ \__| \_______|
 *                     
 * 
 * @author itacsq
 * @date 2018-01-30
 * 
 */
public class LinkedinReader implements CVReader{

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(LinkedinReader.class);
	private LinkedinAnalyzer analyzer = new LinkedinAnalyzer();
	private CV cv;
	
	
	
	
	
	public CV buildCV(String text) {
		cv = new CV();
				
		cv.setName(analyzer.extractName(text));
		cv.setLocation(analyzer.extractLocation(text));
		cv.setCurrentPosition(analyzer.extractCurrentPosition(text));
		cv.setListWork(analyzer.extractWork(text));
		cv.setListEDU(analyzer.extractEdu(text));
		cv.setSummary(analyzer.extractSummary(text));
		
		return cv;
		
	}
	
	

}
