package mt.com.uom.project.pest.actors;

import mt.com.uom.project.pest.messages.RequestDiffFunctions;
import mt.com.uom.project.pest.messages.RequestVersions;
import mt.com.uom.project.pest.messages.ResultDiffFunctions;
import mt.com.uom.project.pest.messages.ResultVersions;
import mt.com.uom.project.pest.names.NamesService;
import mt.com.uom.project.pest.props.ActorDiffExtractorProps;
import mt.com.uom.project.pest.props.ActorGitVisitorProps;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class ActorPreprocessor extends UntypedActor {

	final String NAME;
	public ActorPreprocessor(String name) {
		this.NAME = name;
	}
	
	ActorRef actorGitVisitor = getContext().actorOf(Props.create(new ActorGitVisitorProps(NamesService.ActorGitVisitor)),NamesService.ActorGitVisitor);
	ActorRef actorDiffExtractor = getContext().actorOf(Props.create(new ActorDiffExtractorProps(NamesService.ActorDiffExtractor)),NamesService.ActorDiffExtractor);
	
	@Override
	public void onReceive(Object msg) throws Exception {
		
		if (msg instanceof RequestDiffFunctions) {
			actorDiffExtractor.tell(msg, getSelf());
		}
		else if (msg instanceof RequestVersions) {
			actorGitVisitor.tell(msg, getSelf());
		}
		else if (msg instanceof ResultDiffFunctions) {
			getContext().parent().tell(msg, getSelf());
		}
		else if (msg instanceof ResultVersions) {
			getContext().parent().tell(msg, getSelf());
		}
		else {
			unhandled(msg);
		}	
		
	}

}
