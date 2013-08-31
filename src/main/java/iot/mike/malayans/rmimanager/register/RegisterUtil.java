package iot.mike.malayans.rmimanager.register;

import com.alibaba.fastjson.JSON;

import iot.mike.malayans.rmimanager.ports.Port;

public class RegisterUtil {
	private RegisterUtil () {}
	
	/**
	 * 返回一个String关于分配的端口
	 * @param port 端口号
	 * @return String
	 */
	public static String getPortJsonStr (int port) {
		Port _port = new Port();
		_port.setPort(port);
		String order = JSON.toJSONString(_port);
		return order;
	}
	
	/**
	 * 从模块的注册信息中返回ModuleInfo
	 * @param jsoninfo
	 * @return ModuleInfo
	 */
	public static ModuleInfo getModule(String jsoninfo) {
		ModuleInfo moduleInfo = JSON.parseObject(jsoninfo, ModuleInfo.class);
		return moduleInfo;
	}
}
