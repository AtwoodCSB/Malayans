package iot.mike.malayans.rmimanager.register;

import java.util.LinkedList;

public class ModuleStatus {
	private LinkedList<ModuleStatusEntry>	moduleStatusEntries		= null;

	public LinkedList<ModuleStatusEntry> getModuleStatusEntries() {
		return moduleStatusEntries;
	}

	public void setModuleStatusEntries(
			LinkedList<ModuleStatusEntry> moduleStatusEntries) {
		this.moduleStatusEntries = moduleStatusEntries;
	}
	
	public void addModuleStatusEntry(ModuleStatusEntry entry) {
		moduleStatusEntries.add(entry);
	}
}
