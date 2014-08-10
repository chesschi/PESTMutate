package mt.com.uom.project.pest.props;

import mt.com.uom.project.pest.actors.ActorCore;
import akka.japi.Creator;

public class ActorCoreProps implements Creator<ActorCore>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2105836528704755479L;
	
	private String name;
	public ActorCoreProps(String name) {
		this.name = name;
	}
	@Override
	public ActorCore create() throws Exception {
		return new ActorCore(name);
	}

}
