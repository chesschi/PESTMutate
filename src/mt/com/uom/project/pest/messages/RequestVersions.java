package mt.com.uom.project.pest.messages;

import java.io.Serializable;

public class RequestVersions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8130356414767252148L;
	
	private int receiptId; 
	private String gitRepositoryLocation;
	private String gitBranch;
	private String username;
	private String password;
	
	public String getGitRepositoryLocation() {
		return gitRepositoryLocation;
	}
	public void setGitRepositoryLocation(String gitRepositoryLocation) {
		this.gitRepositoryLocation = gitRepositoryLocation;
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
	public int getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}
	public String getGitBranch() {
		return gitBranch;
	}
	public void setGitBranch(String gitBranch) {
		this.gitBranch = gitBranch;
	}			
}
