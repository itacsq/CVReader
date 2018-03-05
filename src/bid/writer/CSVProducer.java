package bid.writer;

import java.util.List;

import org.apache.log4j.Logger;

import bid.analyzer.beans.CV;
import bid.general.props.GeneralProps;
import bid.utils.var.FileUtils;

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
public class CSVProducer {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(CSVProducer.class);
	private GeneralProps prop;
	
	private final char sep = ';';
	
	public CSVProducer() {
		prop = new GeneralProps();
	}
	
	private String getHeader() {
		String head = "";
		head += "Nome" + sep;
		head += "Location" + sep;
		head += "Actual_Position" + sep;
		head += "Sezione" + sep;
		head += "ID_Doc" + sep;
		head += "Position" + sep;
		head += "Text" + sep;
		head += "Start_Period" + sep;
		head += "End_Period";
		return head;
	}
	
	public void printList(List<CV> list) {
		String print = "";
		print += getHeader() + "\n";
		for(CV c : list) {
			print += c.toCSV();
		}
		FileUtils.write(prop.getGeneralCVPathOut() + "/" + "output.csv", print, false);
	}
	
	public void printListStr(List<String> list) {
		String print = "";
		print += "info" + "\n";
		for(String str : list) {
			print += str + "\n"; 
		}
		FileUtils.write(prop.getGeneralCVPathOut() + "/" + "outputPlain.csv", print, false);
	}

}
