package se.atg.service.harrykart.rest;

import java.util.List;

public class Response {
	private List<Result> ranking;

	public Response() {
		
	}

	public List<Result> getRanking() {
		return ranking;
	}

	public void setRanking(List<Result> ranking) {
		this.ranking = ranking;
	}
	

}
