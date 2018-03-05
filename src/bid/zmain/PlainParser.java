package bid.zmain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bid.general.props.GeneralProps;
import bid.reader.general.CVDispatcher;
import bid.utils.var.FileUtils;
import bid.writer.CSVProducer;

public class PlainParser {
	
	private CVDispatcher disp;
	private CSVProducer csv;
	private GeneralProps prop;
	
	
	public PlainParser() {
		prop = new GeneralProps();
		disp = new CVDispatcher();
		csv = new CSVProducer();
		
	}
	
	public void run() {
		
		List<String> out = new ArrayList<String>();
		for(File pdf : FileUtils.ls(prop.getGeneralCVPath(), "", "pdf")) {
			System.out.println(disp.read(pdf).replaceAll("\r", " ").replaceAll("\n", " "));
		}
		csv.printListStr(out);
		
	}
	
	public static void main(String[] args) {
		PlainParser pp = new PlainParser();
		pp.run();
	}
	
	


}
