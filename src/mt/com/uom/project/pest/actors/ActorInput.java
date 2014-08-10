package mt.com.uom.project.pest.actors;

import mt.com.uom.project.pest.messages.RequestDiffFunctions;
import mt.com.uom.project.pest.messages.RequestVersions;
import akka.actor.UntypedActor;

public class ActorInput extends UntypedActor {

	final String NAME;
	
	public ActorInput(String name) {
		this.NAME = name;
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof RequestDiffFunctions) {			
			
		}
		else if (msg instanceof RequestVersions) {
			
		}		
		else {
			unhandled(msg);
		}	
		
	}

}
