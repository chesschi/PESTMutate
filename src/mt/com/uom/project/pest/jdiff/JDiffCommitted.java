package mt.com.uom.project.pest.jdiff;

import java.io.Serializable;

public class JDiffCommitted implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4855213862768783002L;
	private String revisionName;
    private String commitTimestamp;
    private String commitShortDescription;
    private String commitDescription;

    /**
     * Explicit constructor.
     * @param revisionName the revisionName
     * @param commitTimestamp the commitTimestamp
     * @param commitShortDescription the commitShortDescription
     * @param commitDescription the commitDescription
     */
    public JDiffCommitted(final String revisionName, final String commitTimestamp, final String commitShortDescription, final String commitDescription) {
        this.revisionName = revisionName;
        this.commitTimestamp = commitTimestamp;
        this.commitShortDescription = commitShortDescription;
        this.commitDescription = commitDescription;
    }
    
    /**
     * Return revision name of the commit.
     * @return the revisionName
     */
    public String getRevisionName() {
        return revisionName;
    }

    /**
     * Set the revision name of the commit.
     * @param revisionName the revisionName to set
     */
    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    /**
     * Get the commit timestamp.
     * @return the commitTimestamp
     */
    public String getCommitTimestamp() {
        return commitTimestamp;
    }

    /**
     * Set the commit timestamp.
     * @param commitTimestamp the commitTimestamp to set
     */
    public void setCommitTimestamp(String commitTimestamp) {
        this.commitTimestamp = commitTimestamp;
    }

    /**
     * Get the commit description.
     * @return the commitDescription
     */
    public String getCommitDescription() {
        return commitDescription;
    }

    /**
     * Set the commit description.
     * @param commitDescription the commitDescription to set
     */
    public void setCommitDescription(String commitDescription) {
        this.commitDescription = commitDescription;
    }

    /**
     * Get the commit short description.
     * @return the commitShortDescription
     */
    public String getCommitShortDescription() {
        return commitShortDescription;
    }

    /**
     * Set the commit short description.
     * @param commitShortDescription the commitShortDescription to set
     */
    public void setCommitShortDescription(String commitShortDescription) {
        this.commitShortDescription = commitShortDescription;
    }
}
