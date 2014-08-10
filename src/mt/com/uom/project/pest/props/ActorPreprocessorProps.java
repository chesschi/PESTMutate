package mt.com.uom.project.pest.props;

import mt.com.uom.project.pest.actors.ActorPreprocessor;
import akka.japi.Creator;

public class ActorPreprocessorProps implements Creator<ActorPreprocessor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666805318137470832L;
	
	private String name;
	public ActorPreprocessorProps(String name) {
		this.name = name;
	}
	
	@Override
	public ActorPreprocessor create() throws Exception {
		return new ActorPreprocessor(name);
	}

}
