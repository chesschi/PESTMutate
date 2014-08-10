package mt.com.uom.project.pest.props;

import mt.com.uom.project.pest.actors.ActorInput;
import akka.japi.Creator;

public class ActorInputProps implements Creator<ActorInput>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3529696530665681331L;

	private String name;
	public ActorInputProps(String name) {
		this.name = name;
	}
	@Override
	public ActorInput create() throws Exception {
		return new ActorInput(name);
	}

}
