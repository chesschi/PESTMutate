package mt.com.uom.project.pest.actors;

import mt.com.uom.project.pest.git.GitManager;
import mt.com.uom.project.pest.messages.RequestVersions;
import mt.com.uom.project.pest.messages.ResultVersions;
import akka.actor.UntypedActor;

public class ActorGitVisitor extends UntypedActor {

	final String NAME;
	
	public ActorGitVisitor(String name) {
		this.NAME = name;
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		
		if (msg instanceof RequestVersions) {
			onProcess((RequestVersions)msg);
		}		
		else {
			unhandled(msg);
		}			
	}
	
	private void onProcess(RequestVersions msg) {
		ResultVersions result = GitManager.INSTANCE.getVersions(msg);
		getSender().tell(result, getSelf());
	}
}
