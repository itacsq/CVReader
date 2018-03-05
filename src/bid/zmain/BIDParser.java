package bid.zmain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import bid.analyzer.beans.CV;
import bid.general.props.GeneralProps;
import bid.reader.general.CVDispatcher;
import bid.utils.var.FileUtils;
import bid.writer.CSVProducer;

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
public class BIDParser {
	
	private final String log4jProp = "props/log4j.properties";
	private Logger logger = Logger.getLogger(BIDParser.class);
	private CVDispatcher disp;
	private CSVProducer csv;
	private GeneralProps prop;
	
	public BIDParser() {
		init();
	}
	
	private void init() {
		PropertyConfigurator.configure(log4jProp);
		
		disp = new CVDispatcher();
		csv = new CSVProducer();
		prop = new GeneralProps();
	}
	
	
	public void run() {
		
		
		List<CV> cvs = new ArrayList<CV>();
		logger.info("Init Extraction Phase");
		for(File pdf : FileUtils.ls(prop.getGeneralCVPath(), "", "pdf")) {
			System.out.println(pdf.getName());
			CV c = disp.getCV(pdf);
			if(c!=null){
				logger.info("-> Analyzed: [" + c.getName() + "]");
				cvs.add(c);
			}
		}
		logger.info("Extraction Terminated");
		csv.printList(cvs);
		logger.info("Results saved in: [" + prop.getGeneralCVPathOut() + "]");
	}
	
	
	
	public static void main(String[] args) {
		BIDParser bid = new BIDParser();
		bid.run();
	}

}
