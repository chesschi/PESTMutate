package mt.com.uom.project.pest.messages;

import java.io.Serializable;
import java.util.List;

public class MessageTesting implements Serializable {
	
	/**
	 * serial version uid
	 */
	private static final long serialVersionUID = -1414218735731189623L;
	
	private String testingString;
	private int testingInteger;
	private List<String> testingStrings;
	
	public String getTestingString() {
		return testingString;
	}
	public void setTestingString(String testingString) {
		this.testingString = testingString;
	}
	public int getTestingInteger() {
		return testingInteger;
	}
	public void setTestingInteger(int testingInteger) {
		this.testingInteger = testingInteger;
	}
	public List<String> getTestingStrings() {
		return testingStrings;
	}
	public void setTestingStrings(List<String> testingStrings) {
		this.testingStrings = testingStrings;
	}
}
