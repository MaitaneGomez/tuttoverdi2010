package RMIClient.gui;

import RMIClient.RehearsalController;

import rehearsalServer.RehearsalRMIDTO;

import java.util.Observer;

import javax.swing.JFrame;

/**
 * CHECK THE GUI SOURCE CODE PROVIDED AS A REFERENCE FEEL FREE TO ADD OR
 * ORGANIZE THE GUI AS YOU PLEASE KEEP IT SIMPLE
 * 
 */

public class RMIClientGUI extends JFrame implements Observer {
	
	private RehearsalController controller;

	public RMIClientGUI(RehearsalController controller)
	{
		//inicializar la ventana---lo podemos hacer en una funcion fuera
		this.controller = controller;
		this.controller.addLocalObserver(this);
	}
	
	public void update(java.util.Observable o, Object arg) {

	}
}