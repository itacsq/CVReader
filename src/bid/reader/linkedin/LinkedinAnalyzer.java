package bid.reader.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import bid.analyzer.beans.SectionEDU;
import bid.analyzer.beans.SectionSummary;
import bid.analyzer.beans.SectionWork;
import bid.reader.cv.CVAnalyzer;

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
public class LinkedinAnalyzer implements CVAnalyzer {
	
	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(LinkedinAnalyzer.class);
	
	
	private final String rgx_exp = "(?s)^experience(.*?)^education"; 
	private final String rgx_edu = "(?s)education(.*?)profile";
	private final String rgx_sum = "(?s)summary(.*?)experience";
	
	
	private final String rgx_work_full= "[\\n\\w\\s]{1,}\\sat\\s.*?\\(.*?\\)";
	private final String rgx_work_changed = "(?s)<work>(.*?)<#work>";
	
	
	private final String rgx_work_fulldate = "((?:\\w+).*[\\d]{4}).*-.*(Present|((?:jan.*|feb.*|mar.*|apr.*|may.*|jun.*|jul.*|aug.*|sep.*|oct.*|nov.*|dec.*).*[\\d]{4})).*\\((.*)\\)";
	private final int rgx_work_start = 1;
	private final int rgx_work_end = 2;
	private final int rgx_work_elapsed = 4;
	
	
	private final String rgx_univ = "(.*),(.*),((.*)-(.*))";
	private final int rgx_edu_title_1 = 1;
	private final int rgx_edu_title_2 = 2;
	private final int rgx_edu_start = 4;
	private final int rgx_edu_end = 5;
	
	
	
	private final String rgx_univ_changed = "(?s)<edu>(.*?)<#edu>";
	
	
	
	
	private final int numline_name = 0;
	private final int numline_location = 1;
	private final int numline_position = 2;
	
	public String extractName(String text) {
		return extractLine(text, numline_name);
	}
	
	public String extractLocation(String text) {
		return extractLine(text, numline_location);
	}
	
	public String extractCurrentPosition(String text) {
		return extractLine(text, numline_position);
	}
	
	private String extractLine(String text, int numLine) {
		String ret = "";
		
		List<String> wholeText = Arrays.asList(text.split("\n"));
		
		// System.out.println("Line: " + numLine + " - WL: " + wholeText.get(numLine));
		if(wholeText.size()>numLine)
			ret = wholeText.get(numLine).replaceAll("\r", "");
		return ret;
	}
	
	
	
	private String extractPart(String regex, String text) {
		String ret = "";
		Pattern p = Pattern.compile(regex, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		boolean found = m.find();
		if(found) {
			ret = m.group(1);
			ret = ret.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		}
		return ret;
	}
	
	
		
	
	
	public List<SectionEDU> extractEdu(String input){
		Pattern p;
		Matcher m;
		List<SectionEDU> secs = new ArrayList<SectionEDU>();
		
		
		String name = input.split("\r")[0];
		String text = extractPart(rgx_edu, input).replaceAll(name, "");
		
		p = Pattern.compile(rgx_univ);
		m = p.matcher(text);
		String edu_title_1 = "";
		String edu_title_2 = "";
		String edu_init = "";
		String edu_end = "";
		int nSec = 0;
		
		while(m.find()) {
			edu_title_1 = m.group(rgx_edu_title_1);
			edu_title_2 = m.group(rgx_edu_title_2);
			edu_init = m.group(rgx_edu_start);
			edu_end = m.group(rgx_edu_end);
			
			SectionEDU s = new SectionEDU();
			s.setTitle(edu_title_1 + " # " + edu_title_2);
			s.setStart(edu_init);
			s.setEnd(edu_end);
			secs.add(s);
			
			nSec ++;
			
		}
		
		p = Pattern.compile(rgx_univ);
		m = p.matcher(text);
		String text_c = "<edu>" + m.replaceAll("<#edu><edu>") + "<#edu>";
		
		p = Pattern.compile(rgx_univ_changed, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
		m = p.matcher(text_c);
		
		nSec = 0;
		
		
		while(m.find()) {
			if(nSec<secs.size()) {
				String edu_campus = m.group(1);
				edu_campus = edu_campus.replace("\n", "").replace("\r", "");
				secs.get(nSec).setPlace(edu_campus);
			}
			nSec ++;
		}
		
		return secs;
	}
	
	
	public List<SectionWork> extractWork(String input) {
		String text = extractPart(rgx_exp, input);
		
		Pattern p;
		Matcher matcher;
		String descs; 
		List<SectionWork> secs = new ArrayList<SectionWork>();
		
		
		
		
		
		p = Pattern.compile(rgx_work_full, Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
		matcher = p.matcher(text);
		
		
		
		int nSec = 0;
		while (matcher.find()) {
			String intest = matcher.group(0);
			
			Pattern sp = Pattern.compile(rgx_work_fulldate, Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
			Matcher sm = sp.matcher(intest);
			String job_time = "";
			String job_start = "";
			String job_end = "";
			String job_elapsed = "";
			String job_title = "";
			if(sm.find()) {
				job_time = sm.group(0);
				job_start = sm.group(rgx_work_start);
				job_end = sm.group(rgx_work_end);
				job_elapsed = sm.group(rgx_work_elapsed);
			}
			job_title = sm.replaceAll(" ");
			SectionWork sec = new SectionWork();
			sec.setTime(job_time);
			sec.setStartTime(job_start);
			sec.setEndTime(job_end);
			sec.setDuration(job_elapsed);
			sec.setTitle(job_title);
			secs.add(sec);
			nSec++;
		}
		
		
		
		
		
		nSec=0;
		descs = matcher.replaceAll("\n<#work><work>") + "<#work>";
		p = Pattern.compile(rgx_work_changed, Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
		matcher = p.matcher(descs);
		while (matcher.find()) {
			for (int i = 1; i <= matcher.groupCount(); i++) {
		        String job_desc = matcher.group(i).replaceAll("\\n", "");
		        secs.get(nSec).setDesc(job_desc);
		    }
			nSec++;
		}
		
		
		return secs;
	}
	
	
	
	public SectionSummary extractSummary(String input) {

		String text = extractPart(rgx_sum, input).replace("\n", "").replace("\r", "");
		SectionSummary summary = new SectionSummary();
		summary.setDesc(text);
		
		return summary;
	}
	
	
	
	

}
