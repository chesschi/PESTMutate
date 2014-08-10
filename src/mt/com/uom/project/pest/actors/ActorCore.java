package mt.com.uom.project.pest.actors;

import java.util.concurrent.TimeUnit;

import mt.com.uom.project.pest.messages.RequestDiffFunctions;
import mt.com.uom.project.pest.messages.RequestVersions;
import mt.com.uom.project.pest.messages.ResultDiffFunctions;
import mt.com.uom.project.pest.messages.ResultVersions;
import mt.com.uom.project.pest.names.NamesService;
import mt.com.uom.project.pest.props.ActorInputProps;
import mt.com.uom.project.pest.props.ActorOutputProps;
import mt.com.uom.project.pest.props.ActorPreprocessorProps;
import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;

public class ActorCore extends UntypedActor {

	private static SupervisorStrategy strategy = 
			new OneForOneStrategy(	10, 
									Duration.create(10, TimeUnit.SECONDS),
							      	new Function<Throwable, Directive>(){
										public Directive apply(Throwable t) {
											if (t instanceof ArithmeticException) {
												return resume();
											}
											else if (t instanceof NullPointerException) {
												return restart();
											}
											else if (t instanceof IllegalArgumentException) {
												return stop();
											}
											else if (t instanceof RuntimeException) {
												return stop();
											}
											else {
												return escalate();
											}
										}
									});
	
	public static SupervisorStrategy getStrategy() {
		return strategy;
	}
	
	final String NAME;
	
	ActorRef actorInput	= getContext().actorOf(Props.create(new ActorInputProps(NamesService.ActorInput)),NamesService.ActorInput);
	ActorRef actorOutput = getContext().actorOf(Props.create(new ActorOutputProps(NamesService.ActorOutput)),NamesService.ActorOutput);
	ActorRef actorPreprocessor = getContext().actorOf(Props.create(new ActorPreprocessorProps(NamesService.ActorPreprocessor)),NamesService.ActorPreprocessor);
		
	public ActorCore(String name) {
		this.NAME = name;
	}
			
	@Override
	public void onReceive(Object msg) throws Exception {
		
		actorInput.tell(msg, getSelf());
		
		if (msg instanceof RequestDiffFunctions) {			
			actorPreprocessor.tell(msg, getSelf());
		}
		else if (msg instanceof RequestVersions) {
			actorPreprocessor.tell(msg, getSelf());
		}
		else if (msg instanceof ResultDiffFunctions) {
			actorOutput.tell(msg, getSelf());
		}
		else if (msg instanceof ResultVersions) {
			actorOutput.tell(msg, getSelf());
		}
		else {
			unhandled(msg);
		}			
	}
}
