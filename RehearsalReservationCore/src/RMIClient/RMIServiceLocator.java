package RMIClient;

import rehearsalServer.IOperaRehearsalServer;

public class RMIServiceLocator {
	private IOperaRehearsalServer service;

	public IOperaRehearsalServer getService() {
		return service;
	}
}
