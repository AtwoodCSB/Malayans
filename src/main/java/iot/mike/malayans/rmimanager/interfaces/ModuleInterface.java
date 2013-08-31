package iot.mike.malayans.rmimanager.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 这是所有模块必须完成的接口
 * @author mikecoder
 * @date 2013-7-27
 */
public interface ModuleInterface extends Remote{
	
	public void init() throws RemoteException;
	
	public void start() throws RemoteException;
	
	public void stop() throws RemoteException;
	
	public String getStatus() throws RemoteException;
	
	public String doCommand(String command) throws RemoteException;
	
	public String getDescription() throws RemoteException;
}
