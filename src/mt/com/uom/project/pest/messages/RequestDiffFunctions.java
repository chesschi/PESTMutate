package mt.com.uom.project.pest.messages;

import java.io.Serializable;

public class RequestDiffFunctions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1296028151338228404L;
	
	
	private int receiptId; 
	private String version1;
	private String version2;
	private String gitRepositoryLocation;
	private String gitBranch;
	private String username;
	private String password;
	
	public String getVersion1() {
		return version1;
	}
	public void setVersion1(String version1) {
		this.version1 = version1;
	}
	public String getVersion2() {
		return version2;
	}
	public void setVersion2(String version2) {
		this.version2 = version2;
	}
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public String getGitRepositoryLocation() {
		return gitRepositoryLocation;
	}
	public void setGitRepositoryLocation(String gitRepositoryLocation) {
		this.gitRepositoryLocation = gitRepositoryLocation;
	}
	public String getGitBranch() {
		return gitBranch;
	}
	public void setGitBranch(String gitBranch) {
		this.gitBranch = gitBranch;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
