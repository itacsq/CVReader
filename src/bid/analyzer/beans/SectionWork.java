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
public class SectionWork {
	
	private String title;
	private String time;
	private String desc;
	private String startTime;
	private String endTime;
	private String duration;
	
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title.replaceAll("\\r", "").replaceAll("\\n", "");
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time.replaceAll("\\r", "").replaceAll("\\n", "");
	}
	public String getDesc() {
		return desc.replaceAll("\\r", "").replaceAll("\\n", "");
	}
	public void setDesc(String desc) {
		this.desc = desc.replaceAll("\\r", "").replaceAll("\\n", "");
	}
	@Override
	public String toString() {
		String ret = ",{" + "\n";
		ret += "\t\"time\":\"" + time + "\", \n";
		ret += "\t\"title\":\"" + title + "\", \n";
		ret += "\t\"start\":\"" + startTime + "\", \n";
		ret += "\t\"end\":\"" + endTime + "\", \n";
		ret += "\t\"elapsed\":\"" + duration + "\", \n";
		ret += "\t\"body\":\"" + getDesc() + "\"}"; 
		return ret;
	}
	
	
	public boolean isNull() {
		if(title == null)
			return true;
		if(time==null)
			return true;
		return false;
	}
	

}
