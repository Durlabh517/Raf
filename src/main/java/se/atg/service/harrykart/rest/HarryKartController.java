package se.atg.service.harrykart.rest;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HarryKartController {

    @PostMapping( value = "/play", consumes = "application/xml")
    public Response playHarryKart(@RequestBody HarryKart kart) {
    	List<Participant> participants = kart.getStartList();
    	int totalParticipant = participants.size();
    	int numberOfLoops = kart.getNumberOfLoops();
    	List<Integer> timeTakenByParticipant = new ArrayList<Integer>();
    	List<Boolean> completionFlag = new ArrayList<Boolean>();
    	for(int participant=1; participant<=totalParticipant; participant++) {
    		timeTakenByParticipant.add(0);
    		completionFlag.add(false);
    	}
    	for(int participant=0; participant<totalParticipant; participant++) {
    		final int thisParticipant = participant;
    		Thread lane = new Thread(()->{	//new Thread(new Thread(){public void run(){}})
    			try {
	    			int baseSpeed = participants.get(thisParticipant).getBaseSpeed();
	    			for(int loop=0; loop<numberOfLoops; loop++) {
	    				for(int j=1; j<= 1000; j += baseSpeed) {
	    					int timeTaken = timeTakenByParticipant.get(thisParticipant)+1;
	    					timeTakenByParticipant.set(thisParticipant, timeTaken);
	    				}
	    				if(loop < numberOfLoops-1) {
		    				int powerUp = kart.getPowerUps().get(loop).get(thisParticipant).get("");
		    				baseSpeed += powerUp;
	    				}
	    			}
    			}finally {
    				completionFlag.set(thisParticipant, true);
    			}
    		});
    		lane.start();
    	}
    	BUFFER: while(true) {
    		for(Boolean flag: completionFlag) {
    			if(flag == false) continue BUFFER;
    		}
    		break;
    	}
    	
    	for(int i=0; i<totalParticipant-1; i++ ) {
        	for(int j=i+1; j<totalParticipant; j++ ) {
        		if(timeTakenByParticipant.get(j) < timeTakenByParticipant.get(i)) {
        			int temp = timeTakenByParticipant.get(j);
        			timeTakenByParticipant.set(j, timeTakenByParticipant.get(i));
        			timeTakenByParticipant.set(i, temp);
        			Participant tempParticipant = participants.get(j);
        			participants.set(j, participants.get(i));
        			participants.set(i, tempParticipant);
        		}
        	}
    	}
    	Response response=new Response();
    	response.setRanking(new ArrayList<>());
    	
    	for(int i=1;i<=3;i++) {
	    	Result result=new Result();
	    	result.setPosition(i);
	    	result.setHorse(participants.get(i-1).getName());
	    	response.getRanking().add(result);
    	}
        return response;
        
    }
}

