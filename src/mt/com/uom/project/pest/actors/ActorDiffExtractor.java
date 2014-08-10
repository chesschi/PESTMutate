package mt.com.uom.project.pest.actors;

import mt.com.uom.project.pest.git.GitManager;
import mt.com.uom.project.pest.messages.RequestDiffFunctions;
import mt.com.uom.project.pest.messages.ResultDiffFunctions;
import akka.actor.UntypedActor;

public class ActorDiffExtractor extends UntypedActor {

	final String NAME;
	
	public ActorDiffExtractor(String name) {
		this.NAME = name;
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		
		if (msg instanceof RequestDiffFunctions) {
			onProcess((RequestDiffFunctions) msg);
		}		
		else {
			unhandled(msg);
		}			
	}
	
	private void onProcess(RequestDiffFunctions msg) {
		ResultDiffFunctions result = GitManager.INSTANCE.getDiffFunctions(msg);
		getSender().tell(result, getSelf());
	}
	
}
