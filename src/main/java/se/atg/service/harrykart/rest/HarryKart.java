package se.atg.service.harrykart.rest;

import java.util.List;
import java.util.Map;

public class HarryKart {
	private int numberOfLoops;
	private List<Participant> startList;
	private List<List<Map<String, Integer>>> powerUps;
	
	public HarryKart() {
		
	}

	public int getNumberOfLoops() {
		return numberOfLoops;
	}

	public void setNumberOfLoops(int numberOfLoops) {
		this.numberOfLoops = numberOfLoops;
	}

	public List<Participant> getStartList() {
		return startList;
	}

	public void setStartList(List<Participant> startList) {
		this.startList = startList;
	}

	public List<List<Map<String, Integer>>> getPowerUps() {
		return powerUps;
	}

	public void setPowerUps(List<List<Map<String, Integer>>> powerUps) {
		this.powerUps = powerUps;
	}
}
