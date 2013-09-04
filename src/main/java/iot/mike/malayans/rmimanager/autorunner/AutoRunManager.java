package iot.mike.malayans.rmimanager.autorunner;

import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class AutoRunManager {
	private File						jarDir					= null;
	private HashMap<String, File>		jarfiles				= null;
	private TimerTask					checkFilesTask			= null;
	private Timer						checkFilesTimer			= null;
	private boolean						isInit					= false;
	
	private AutoRunManager() {
	}
	
	private static class AutoRunManagerHolder {
		public static AutoRunManager instance = new AutoRunManager();
	}
	
	public static AutoRunManager getInstance() {
		return AutoRunManagerHolder.instance;
	}
	
	public void init() {
		if (!isInit) {
			jarDir = new File("jars");
			if (!jarDir.exists()) {
				jarDir.mkdirs();
				System.out.println("Create File");
			}
			jarfiles = new HashMap<String, File>();
		}
	}
	
	public void start() {
		checkFilesTask = new TimerTask() {
			@Override
			public void run() {
				refresh();
			}
		};
		checkFilesTimer = new Timer();
		checkFilesTimer.scheduleAtFixedRate(checkFilesTask, 0, 1000 * 10);
	}
	
	public void stop() {
		checkFilesTask.cancel();
		checkFilesTimer.cancel();
		checkFilesTask = null;
		checkFilesTimer = null;
		
		jarfiles.clear();
	}
	
	public void refresh() {
		init();
		File[] jars = jarDir.listFiles();
		if (jars != null) {
			for (File jar : jars) {
				boolean isOk = false;
				if (!jarfiles.containsKey(jar.getName())) {
					for (int i = 0; i < 5; i++) {
						//给予5次机会，如果无法加载，就删除
						try {
							JarLoaderUtil.loadJar(jar);
							jarfiles.put(jar.getName(), jar);
							isOk = true;
							break;
						} catch (Exception e) {}
					}
					if (!isOk) {
						jar.delete();
					}
				}
			}
		}
	}
	
	public HashMap<String, File> getJars() {
		return jarfiles;
	}
}
