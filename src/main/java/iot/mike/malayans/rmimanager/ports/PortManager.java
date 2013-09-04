package iot.mike.malayans.rmimanager.ports;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Stack;

import org.apache.log4j.Logger;

public class PortManager {
	private static final int 		INITPORT			= 9403;
	private boolean					isInit 				= false;
	private Stack<Integer> 			ports 				= null;			
	private int 					maxPort 			= INITPORT;
	private Logger 					logger 				= null;
	
	private PortManager (){};
	
	private static class PortManagerHolder {
		public static PortManager instance 			= new PortManager();
	}
	
	public static PortManager getInstance() {
		return PortManagerHolder.instance;
	}
	
	public void init() {
		if (!isInit) {
			ports 						= new Stack<Integer>();
			logger						= Logger.getLogger(PortManager.class);
			isInit 						= true;
		}
	}
	
	/**
	 * 得到一个空闲端口
	 * @return int
	 */
	public int getPort() {
		init();
		if (ports.size() > 0) {
			return ports.pop();
		}else {
			while (true) {
				try {
					ServerSocket serverSocket = new ServerSocket(maxPort);
					serverSocket.close();
					return maxPort;
				} catch (IOException e) {
					maxPort++;
					continue;
				}
			}
		}
	}
	
	/**
	 * 使用方归还一个port
	 * @param port 端口号
	 */
	public void returnPort(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			serverSocket.close();
			//确认是不是可以使用了
			synchronized (this) {
				ports.push(port);
			}
		} catch (IOException e) {
			logger.warn("Port:" + port + "can't be used");
		}
	}
}
