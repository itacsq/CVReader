package bid.analyzer.beans;

import java.util.List;

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
public class CV {
	
	private final String summaryName = "SUMMARY";
	private final String educationName = "EDUCATION";
	private final String workName = "EXPERIENCE";
	private final String sep = ";";
	
	private String name;
	private String location;
	private String currentPosition;
	
	private List<SectionEDU> listEDU;
	private List<SectionWork> listWork;
	private SectionSummary summary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<SectionEDU> getListEDU() {
		return listEDU;
	}
	public void setListEDU(List<SectionEDU> listEDU) {
		this.listEDU = listEDU;
	}
	public List<SectionWork> getListWork() {
		return listWork;
	}
	public void setListWork(List<SectionWork> listWork) {
		this.listWork = listWork;
	}
	public String getCurrentPosition() {
		return currentPosition;
	}
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	
	
	public SectionSummary getSummary() {
		return summary;
	}
	public void setSummary(SectionSummary summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		String ret = "";
		ret += "{";
		ret += "\"name\":" + "\"" + name + "\"" + ", \n";
		ret += "\"location\":" + "\"" + location + "\"" + ", \n";
		ret += "\"position\":" + "\"" + currentPosition + "\"" + ", \n";
		
		ret += "\"jobs\": [" ;
		for(int i=0; i<listWork.size(); i++) {
			if(i==0) {
				ret+= listWork.get(i);
			}else {
				ret+= sep + listWork.get(i);
			}
		}
		ret += "]," + "\n";
		ret += "\"trainings\": [" ;
		for(int i=0; i<listEDU.size(); i++) {
			if(i==0) {
				ret+= listEDU.get(i);
			}else {
				ret+= sep + listEDU.get(i);
			}
		}
		ret += "]" + "\n";
		ret += "\n" + "}";
		
		
		
		return ret;
	}
	
	
	public String toCSV() {
		String ret = "";
		
		// Summary
		int nDoc = 0;
		ret += getLine(name, location, currentPosition, summaryName, ++nDoc, "", summary.getDesc(), "", "") 
				+ "\n";
		for(SectionWork sec : listWork)
			ret += getLine(name, location, currentPosition, workName, ++nDoc, sec.getTitle(), sec.getDesc(), sec.getStartTime(), sec.getEndTime())
				+ "\n";
		for(SectionEDU sec : listEDU)
			ret += getLine(name, location, currentPosition, educationName, ++nDoc, sec.getTitle(), sec.getPlace(), sec.getStart(), sec.getEnd())
				+ "\n";
		return ret;
	}
	
	
	private String getLine(String name, String location, String currentPosition, String sectionName, int idDoc, String position, String text, String start, String end) {
		String ret = "";
		ret += "\"" + name + "\"" + sep;
		ret += "\"" + location + "\"" + sep;
		ret += "\"" + currentPosition + "\"" + sep;
		ret += "\"" + sectionName + "\"" + sep;
		ret += "\"" + idDoc + "\"" + sep;
		ret += "\"" + position + "\"" + sep;
		ret += "\"" + text + "\"" + sep;
		ret += "\"" + start + "\"" + sep;
		ret += "\"" + end + "\"" + "";
		return ret;
	}
	
	

}
