package iot.mike.malayans.rmimanager.setting;

import junit.framework.TestCase;

public class SettingManagerTest extends TestCase{
	
	public void testSetting() {
		SettingManager settingManager = SettingManager.getInstance();
		settingManager.init();
		settingManager.getProperties();
		assertEquals("9400", 
				settingManager.getProperties().get(Setting.DataInPort));
		assertEquals("9401", 
				settingManager.getProperties().get(Setting.DataOutPort));
	}
}
