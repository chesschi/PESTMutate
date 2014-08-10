package mt.com.uom.project.pest.messages;

import java.io.Serializable;
import java.util.List;

import mt.com.uom.project.pest.jdiff.JDiffDifferences;

public class ResultDiffFunctions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6711983374803267612L;
	
	private RequestDiffFunctions request; 	
	private List<String> diffObjects;	
	
	public List<String> getDiffObjects() {
		return diffObjects;
	}
	public void setDiffObjects(List<String> diffObjects) {
		this.diffObjects = diffObjects;
	}
	public RequestDiffFunctions getRequest() {
		return request;
	}
	public void setRequest(RequestDiffFunctions request) {
		this.request = request;
	}
	
	
}
