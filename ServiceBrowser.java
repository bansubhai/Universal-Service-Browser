package UniversalServiceBrowser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;

public class ServiceBrowser {
	
	JPanel mainPanel;
	JComboBox serviceList;
	ServiceServer server;
	
	public void buildGui() {
	
		JFrame frame = new JFrame("RMI Browser");
		this.mainPanel = new JPanel();
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
		Object[] services = this.getServicesList();
		this.serviceList = new JComboBox<>(services);
		frame.getContentPane().add(BorderLayout.NORTH, this.serviceList);
		this.serviceList.addActionListener(new MyListListener());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	void loadService(Object serviceSelection) {
		try{
			Service svc = server.getService(serviceSelection);
			
			this.mainPanel.removeAll();
			this.mainPanel.add(svc.getGuiPanel());
			this.mainPanel.validate();
			this.mainPanel.repaint();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Object[] getServicesList(){
		Object[] services = null;
		
		try {
			this.server = (ServiceServer) Naming.lookup("rmi://127.0.0.1/ServiceServer");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			services = this.server.getServiceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return services;
	}
	
	class MyListListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			loadService(serviceList.getSelectedItem());
		}
	}
	
	public static void main(String[] args) {
		new ServiceBrowser().buildGui();
	}
	
}
