package iot.mike.malayans.rmimanager.autorunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoader {
	
	private static final String className =
			"iot.mike.malayans.module.client.MalayansClient";
	private static final String methodName = 
			"registModule";
	
	@SuppressWarnings("rawtypes")
	public void loadJar(File jarfile)
			throws ClassNotFoundException,
				NoSuchMethodException, 
				SecurityException, 
				IllegalAccessException, 
				IllegalArgumentException, 
				InvocationTargetException, 
				InstantiationException, 
				IOException {
		
		URLClassLoader classLoader = 
				new URLClassLoader(new URL[] {jarfile.toURI().toURL() });
		
		Class clazz = classLoader.loadClass(className);
		
		@SuppressWarnings("unchecked")
		Method method = clazz.getDeclaredMethod(methodName, null);
		method.invoke(clazz.newInstance(), null);
		
		classLoader.close();
	}
}
