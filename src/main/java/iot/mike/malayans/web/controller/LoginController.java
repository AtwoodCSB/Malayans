package iot.mike.malayans.web.controller;

import iot.mike.malayans.rmimanager.setting.Setting;
import iot.mike.malayans.rmimanager.setting.SettingManager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	private Logger 				logger 			= Logger.getLogger(LoginController.class);
	private SettingManager		settingManager	= SettingManager.getInstance();
	
	@RequestMapping(value="/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="/logincheck", method=RequestMethod.POST)
	public String logincheck(
			@RequestParam("Username") String username,
			@RequestParam("Password") String password) {
		settingManager.init();
		logger.info(username + " : " + password);
		if (username.equals(Setting.str_USerName)
				&& password.equals(Setting.str_Password)) {
			return "index";
		}
		return "login";
	}
	
	@RequestMapping(value="/logincheck", method=RequestMethod.GET)
	public String logincheck() {
		settingManager.init();
		return "index";
	}
}
