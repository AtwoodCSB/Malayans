package iot.mike.malayans.rmimanager.register;

import iot.mike.malayans.rmimanager.ports.PortManager;
import iot.mike.malayans.rmimanager.setting.Setting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class RegisterThread extends Thread{
	
	private Logger logger 								= null;
	private ServerSocket serverSocket 					= null;
	private Socket clientSocket							= null;
	private PortManager	portManager 					= null;
	private RegisterManager registerManager				= null;
	
	public void init () {
		logger = Logger.getLogger(RegisterThread.class);
		portManager = PortManager.getInstance();
	}
	
	@Override
	public void run () {
		if (logger == null) {
			logger = Logger.getLogger(RegisterThread.class);
		}
		
		try {
			serverSocket = new ServerSocket(Setting.int_DataInPort);
			while (!Thread.interrupted()) {
				try {
					clientSocket = serverSocket.accept();
					DataOutputStream writer = 
							new DataOutputStream(clientSocket.getOutputStream());
					DataInputStream reader = 
							new DataInputStream(clientSocket.getInputStream());
					
					String moduleinfoStr = reader.readUTF();
					
					ModuleInfo moduleInfo = RegisterUtil.getModule(moduleinfoStr);
					
					int port = portManager.getPort();
					
					String order = RegisterUtil.getPortJsonStr(port);
					writer.writeUTF(order);
					writer.flush();
					
					String receive = reader.readUTF();
					if (receive.contains("ok")) {
						registerManager.registModule(moduleInfo.getId(), port);
						logger.info("RegisteModuleID:" + moduleInfo.getId() 
								+ "Description:" + moduleInfo.getDescription());
					}
					clientSocket.close();
				} catch (IOException e) {
					logger.warn("Module Registes Failed!");
				}
			}
		} catch (IOException e) {
			logger.warn("ServerSocket is down!");
			System.exit(0);
		} finally {
			try {serverSocket.close();} 
			catch (IOException e) {}
			serverSocket = null;
		}
	}
	
	public void closeSocket() {
		try {
			serverSocket.close();
			serverSocket = null;
		} catch (Exception e) {}
	}
}
