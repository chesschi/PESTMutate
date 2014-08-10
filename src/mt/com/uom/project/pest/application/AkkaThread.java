package mt.com.uom.project.pest.application;

import java.util.concurrent.ConcurrentLinkedQueue;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import mt.com.uom.project.pest.names.NamesService;
import mt.com.uom.project.pest.props.ActorCoreProps;

public class AkkaThread implements Runnable {
	
	private enum STATE {
		STATE_INIT,
		STATE_NORMAL,
		STATE_END
	}
	
	private STATE m_state = STATE.STATE_INIT;
	private ActorSystem actorSystem = null;
	private ActorRef actorCore = null;
	
	private ConcurrentLinkedQueue<Object> requestQueue = new ConcurrentLinkedQueue<Object>();
	
	private STATE getState() {
		return m_state;
	}

	private void setState(STATE state) {
		this.m_state = state;
	}
	
	public void shutdown() {
		Thread.currentThread().interrupt();
	}
	
	public void receiveRequest(Object msg) {
		requestQueue.add(msg);
	}
	
	public Object retrieveRequest() {
		return requestQueue.poll();
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
            try {
            	processTask();
            	//sleep 10 millisecond
                Thread.sleep(1000);               
            } catch (InterruptedException e) {
                
            }
        }		
	}
	
	private void processTask() {
		switch (getState()) {
		case STATE_INIT: 	processInitMode();		break;
		case STATE_NORMAL:	processNormalMode();	break;
		case STATE_END:		
		default:			processEndMode();		break;
		}
	}
	
	private void processInitMode() {
		
		if (null == actorSystem) {
			actorSystem = ActorSystem.create(NamesService.AkkaSystemName);
			actorCore = actorSystem.actorOf(Props.create(new ActorCoreProps(NamesService.ActorCore)),NamesService.ActorCore);
		}
		
		setState(STATE.STATE_NORMAL);
	}
	
	private void processNormalMode() {
		Object msg = retrieveRequest();
		if (null!=msg) {
			actorCore.tell(msg, ActorRef.noSender());
		} 
	}
	
	private void processEndMode() {
		if (null!=actorSystem) {
			actorSystem.shutdown();
		}
	}
}

