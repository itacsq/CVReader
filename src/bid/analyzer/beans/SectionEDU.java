package bid.analyzer.beans;

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
public class SectionEDU {
	
	
	private String start;
	private String end;
	private String place;
	private String title;
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret += "{";
		ret += "\"place\":" + "\"" + place +  "\"" + "\n";
		ret += "\"title\":" + "\"" + title +  "\"" + "\n";
		ret += "}";
		return ret;
	}
	
	

}
