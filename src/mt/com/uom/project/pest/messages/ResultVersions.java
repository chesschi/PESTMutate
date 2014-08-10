package mt.com.uom.project.pest.messages;

import java.io.Serializable;
import java.util.List;

import mt.com.uom.project.pest.jdiff.JDiffCommitted;

public class ResultVersions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1775902055810206250L;
	
	private RequestVersions request;
	private List<JDiffCommitted> commitObjects;
	
	public RequestVersions getRequest() {
		return request;
	}
	public void setRequest(RequestVersions request) {
		this.request = request;
	}
	public List<JDiffCommitted> getCommitObjects() {
		return commitObjects;
	}
	public void setCommitObjects(List<JDiffCommitted> commitObjects) {
		this.commitObjects = commitObjects;
	}
		
}
