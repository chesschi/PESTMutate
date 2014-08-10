package mt.com.uom.project.pest.props;

import mt.com.uom.project.pest.actors.ActorOutput;
import akka.japi.Creator;

public class ActorOutputProps implements Creator<ActorOutput>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6691833086700570862L;

	private String name;
	public ActorOutputProps(String name){
		this.name = name;
	}
	@Override
	public ActorOutput create() throws Exception {
		return new ActorOutput(name);
	}

}
