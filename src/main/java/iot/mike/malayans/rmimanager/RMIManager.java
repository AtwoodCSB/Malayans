package iot.mike.malayans.rmimanager;

import iot.mike.malayans.rmimanager.ports.PortManager;
import iot.mike.malayans.rmimanager.register.RegisterManager;
import iot.mike.malayans.rmimanager.setting.SettingManager;

public class RMIManager {
	private SettingManager settingManager				= null;
	private RegisterManager registerManager				= null;
	private PortManager portManager						= null;
	
	private RMIManager(){}
	
	private static class RMIManagerHolder {
		public static RMIManager instance = new RMIManager();
	}
	
	public static RMIManager getInstance() {
		return RMIManagerHolder.instance;
	}
	
	public void start() {
		settingManager.init();
		registerManager.init();
		portManager.init();
	}

	public void end() {
		
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
		return null;
	}

	public void init() {
		settingManager = SettingManager.getInstance();
		registerManager = RegisterManager.getInstance();
		portManager = PortManager.getInstance();
	}
}
