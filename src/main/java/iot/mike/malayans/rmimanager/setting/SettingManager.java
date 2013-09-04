package iot.mike.malayans.rmimanager.setting;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SettingManager {
	private File settingFile;			//配置的文件
	private Properties properties;		//这是系统配置
	private Logger logger;				//日志输出器
	private boolean isInit = false;		//是否初始化
	
	private static class SettingManagerHolder{
		public static SettingManager settingManager = new SettingManager();
	}
	
	/**
	 * 进行初始化
	 */
	public void init(){
		if (isInit) {
			return;
		}
		//-------------------------------
		try {
			if (settingFile.exists()) {
				logger.info("File Exsit....Reading");
				properties.load(new FileReader(settingFile));
				if (checkProperty(properties)) {
					logger.info("Property read complete!");
				}else {
					properties = null;	//读取失败删除
					logger.warn("Property is wrong!");
					throw new Exception();
				}
			}else {
				logger.info("File NULL....Creating");
				//输出默认配置
				properties.put(
						Setting.DataInPort, 
						String.valueOf(Setting.int_DataInPort));
				
				properties.put(
						Setting.DataOutPort, 
						String.valueOf(Setting.int_DataOutPort));
				
				properties.put(Setting.UserName, Setting.str_USerName);
				properties.put(Setting.Password, Setting.str_Password);
				
				properties.store(new FileWriter(settingFile), "");
				logger.info("Default setting...\n"
						+ "if you want to change the setting, \n"
						+ "please edit the "
						+ "system.properties file");
			}
			isInit = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.warn("SetingFile can't be read!\n"
					+ "System exit!");
			System.exit(0);
		}
	}
	
	private SettingManager() {
		properties = new Properties();
		settingFile = new File("system.properties");
		logger = Logger.getLogger(SettingManager.class);
	}
	
	public static SettingManager getInstance() {
		return SettingManagerHolder.settingManager;
	}
	
	/**
	 * 检查读取的设置是否正确
	 * @param properties 输入的设置
	 * @return boolean
	 */
	private boolean checkProperty(Properties properties) {
		if (properties != null 
				&& properties.get(Setting.DataInPort) != null
				&& properties.get(Setting.DataOutPort) != null
				&& properties.get(Setting.UserName) != null
				&& properties.get(Setting.Password) != null) {
			try {
				Setting.int_DataInPort = 
						Integer.valueOf(
								properties.getProperty(Setting.DataInPort));
				Setting.int_DataOutPort = 
						Integer.valueOf(
								properties.getProperty(Setting.DataOutPort));
				Setting.str_USerName = properties.getProperty(Setting.UserName);
				Setting.str_Password = properties.getProperty(Setting.Password);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 返回系统设置
	 * @return property
	 */
	public Properties getProperties() {
		if (checkProperty(properties)) {
			return (Properties) properties.clone();
		}
		return null;
	}
}
