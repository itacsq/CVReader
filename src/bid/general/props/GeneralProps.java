package bid.general.props;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

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
public class GeneralProps {
	
	private static final String path = "props/config.properties";
	private Properties prop;
	
	private final String key_general_cv_path = "general.cv.path";
	private final String key_general_cv_path_out = "general.cv.path.out";
	
	public GeneralProps() {
		prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getGeneralCVPath() {
		if(prop!=null)
			return prop.getProperty(key_general_cv_path);
		return "";
	}
	public String getGeneralCVPathOut() {
		if(prop!=null)
			return prop.getProperty(key_general_cv_path_out);
		return "";
	}
	

}
