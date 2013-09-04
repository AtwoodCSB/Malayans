package iot.mike.malayans.rmimanager.ports;

import junit.framework.TestCase;

public class PortManagerTest extends TestCase{
	
	public void test() {
		PortManager portManager = PortManager.getInstance();
		System.out.println(portManager.getPort());
	}
}
