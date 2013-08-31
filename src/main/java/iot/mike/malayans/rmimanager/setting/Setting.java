package iot.mike.malayans.rmimanager.setting;

import java.util.ArrayList;
import java.util.Map;

public class Setting {
	public final static String DataInPort					= "DataInPort";
	public final static String DataOutPort					= "DataOutPort";
	
	//服务器注册端口
	public static int int_DataInPort 					= 9400;
	//服务器对外接口端口
	public static int int_DataOutPort 				= 9401;
	//服务器运行数据
	public volatile static ArrayList<Map<String, String>> systemStatus 
								= new ArrayList<Map<String,String>>();
	
}
