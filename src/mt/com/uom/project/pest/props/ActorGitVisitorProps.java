package mt.com.uom.project.pest.props;

import mt.com.uom.project.pest.actors.ActorGitVisitor;
import akka.japi.Creator;

public class ActorGitVisitorProps implements Creator<ActorGitVisitor>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355300286855232193L;

	private String name;
	public ActorGitVisitorProps(String name) {
		this.name = name;
	}
	@Override
	public ActorGitVisitor create() throws Exception {
		return new ActorGitVisitor(name);
	}

}
