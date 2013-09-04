package iot.mike.malayans.rmimanager;

import iot.mike.malayans.rmimanager.autorunner.AutoRunManager;
import iot.mike.malayans.rmimanager.ports.PortManager;
import iot.mike.malayans.rmimanager.register.RegisterManager;
import iot.mike.malayans.rmimanager.setting.SettingManager;

public class RMIManager {
	private SettingManager 			settingManager				= null;
	private RegisterManager		 	registerManager				= null;
	private PortManager 			portManager					= null;
	private AutoRunManager 			autoRunManager				= null;
	
	private static final String DESCRIPTION	= 
			"这是管理所有模块的模块，也是系统的核心，就是这样！";
	
	private RMIManager(){
		settingManager = SettingManager.getInstance();
		registerManager = RegisterManager.getInstance();
		portManager = PortManager.getInstance();
		autoRunManager = AutoRunManager.getInstance();
	}
	
	private static class RMIManagerHolder {
		public static RMIManager instance = new RMIManager();
	}
	
	public static RMIManager getInstance() {
		return RMIManagerHolder.instance;
	}
	
	public void start() {
		autoRunManager.start();
		registerManager.start();
	}

	public void restart() {		
	}

	public void stop() {
	}

	public String getStatus() {
		return null;
	}

	public String doCommand(String command) {
		return null;
	}

	public String getDescription() {
		return DESCRIPTION;
	}

	public void init() {
		settingManager.init();
		registerManager.init();
		portManager.init();
		autoRunManager.init();
	}
	
	public static void main(String[] args) {
		RMIManager rmiManager = new RMIManager();
		rmiManager.init();
		rmiManager.start();
	}
}
