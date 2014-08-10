package mt.com.uom.project.pest.props;

import mt.com.uom.project.pest.actors.ActorDiffExtractor;
import akka.japi.Creator;

public class ActorDiffExtractorProps implements Creator<ActorDiffExtractor>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8143132614372611947L;

	private String name;
	public ActorDiffExtractorProps(String name) {
		this.name = name;
	}
	@Override
	public ActorDiffExtractor create() throws Exception {
		return new ActorDiffExtractor(name);
	}

}
