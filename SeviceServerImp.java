package UniversalServiceBrowser;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class SeviceServerImp extends UnicastRemoteObject implements ServiceServer{

	HashMap<Object, Service> serviceList;
	
	protected SeviceServerImp() throws RemoteException {
		setUpServices();
	}

	public void setUpServices() {
		this.serviceList = new HashMap<>();
		this.serviceList.put("Dice Rolling Service", new DiceService());
		this.serviceList.put("Day of the Week Service", new DayOfTheWeekService());
		this.serviceList.put("Visual  Music- Service", new MiniMusicService());
	}

	@Override
	public Object[] getServiceList() throws RemoteException {
		System.out.println("in remote");
		return this.serviceList.keySet().toArray();
	}

	@Override
	public Service getService(Object serviceKey) throws RemoteException {
		return this.serviceList.get(serviceKey);
	}

	
	public static void main(String[] args) {
		try{
			Naming.rebind("ServiceServer", new SeviceServerImp());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Remote service is running");
	}
	
}
