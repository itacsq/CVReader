package bid.reader.cv;

import java.util.List;

import bid.analyzer.beans.SectionEDU;
import bid.analyzer.beans.SectionWork;

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
public interface CVAnalyzer {
	
	public String extractName(String text);
	public String extractLocation(String text);
	public String extractCurrentPosition(String text);
	
	public List<SectionEDU> extractEdu(String text);
	public List<SectionWork> extractWork(String text);
	

}
