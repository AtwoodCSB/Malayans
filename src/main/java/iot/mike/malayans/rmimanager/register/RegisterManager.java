package iot.mike.malayans.rmimanager.register;

import iot.mike.malayans.rmimanager.interfaces.ModuleInterface;
import iot.mike.malayans.rmimanager.ports.PortManager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class RegisterManager {
	
	private HashMap<String, ModuleInterface> modules				= null;
	private HashMap<String, Integer> modulesIP						= null;
	private PortManager portManager 								= null;
	private Logger logger											= null;
	private boolean isInit											= false;
	
	private RegisterManager () {}
	
	private static class RegisterManagerHolder {
		public static RegisterManager instance = new RegisterManager();
	}
	
	public static RegisterManager getInstance () {
		return RegisterManagerHolder.instance;
	}
	
	public void init() {
		if (!isInit) {
			modulesIP 					= new HashMap<String, Integer>();
			modules 					= new HashMap<String, ModuleInterface>();
			portManager 				= PortManager.getInstance();
			logger 						= Logger.getLogger(RegisterManager.class);
		}
	}
	
	/**
	 * 注册模块
	 * @param moduleid
	 * @return boolean 是否注册成功
	 */
	public boolean registModule (String moduleid, int port) {
		if (modules.containsKey(moduleid)
				|| port == 0) {
			return false;
		}//包含或者分配端口失败
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			ModuleInterface comp = 
	        		(ModuleInterface)registry.lookup(moduleid);
			this.addModule(moduleid, port, comp);
			return true;
		} catch (RemoteException e) {
			logger.info("无法连接,注册失败");
			return false;
		} catch (NotBoundException e) {
			logger.info("端口上无绑定,注册失败");
			return false;
		}
	}

	/**
	 * 删除某一个模块
	 * @param moduleid 模块ID
	 * @return 是否删除成功,不包含返回false
	 * @throws RemoteException 
	 */
	public boolean removeModule(String moduleid) 
			throws RemoteException {
		if (modules.containsKey(moduleid)) {
			delModule(moduleid);
		}
		return false;
	}
	
	/**
	 * 开启模块
	 * @param moduleid
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void startModule(String moduleid) 
			throws RemoteException, NotBoundException {
		if (modules.containsKey(moduleid)) {
			modules.get(moduleid).start();
		}
	}

	/**
	 * 卸载
	 * @param moduleid
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public void stopModule(String moduleid)
			throws RemoteException, NotBoundException {
		if (modules.containsKey(moduleid)) {
			modules.get(moduleid).stop();
		}
	}

	/**
	 * 得到模块状态
	 * @param moduleid 
	 * @return JsonString
	 * @throws RemoteException
	 */
	public String getModuleStatus(String moduleid)
			throws RemoteException {
		String status = "";
		if (modules.containsKey(moduleid)) {
			status = modules.get(moduleid).getStatus();
		}
		return status;
	}

	/**
	 * 模块执行既定命令
	 * @param moduleid
	 * @param command
	 * @return String 结果
	 * @throws RemoteException
	 */
	public String doModuleCommand(String moduleid, String command) 
			throws RemoteException {
		String result = "";
		if (modules.containsKey(moduleid)) {
			result = modules.get(moduleid).doCommand(moduleid);
		}
		return result;
	}

	/**
	 * 得到模块的描述
	 * @param moduleid
	 * @return String 描述语句
	 * @throws RemoteException
	 */
	public String getModuleDescription(String moduleid) 
			throws RemoteException {
		String description = "";
		if (modules.containsKey(moduleid)) {
			description = modules.get(moduleid).getDescription();
		}
		return description;
	}
	
	private void addModule(String moduleid, int port, ModuleInterface module) {
		modules.put(moduleid, module);
		modulesIP.put(moduleid, port);
	}
	
	private void delModule(String moduleid) {
		if (modules.containsKey(moduleid)) {
			try {
				this.stopModule(moduleid);
			} catch (RemoteException e) {
			} catch (NotBoundException e) {
			}
			modules.remove(moduleid);
			int port = modulesIP.get(moduleid);
			modulesIP.remove(moduleid);
			portManager.returnPort(port);
		}
	}
}
