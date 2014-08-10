package mt.com.uom.project.pest.jdiff;

import java.io.Serializable;

public class JDiffDifferences implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7061573992285137196L;
	
	private int startDiffLineNumber;
    private int endDiffLineNumber;
    private int operatorStartLineNumberInDiffResults;
    /**
     * Default constructor.
     */
    public JDiffDifferences() {
        this.startDiffLineNumber = 0;
        this.endDiffLineNumber = 0;
    }
    /**
     * Explicit constructor.
     * @param startDiffLineNumber the startDiffLineNumber to set
     * @param endDiffLineNumber the endDiffLineNumber to set
     * @param operatorStartLineNumberInDiffResults the operatorStartLineNumberInDiffResults to set
     */
    public JDiffDifferences(final int startDiffLineNumber, final int endDiffLineNumber, final int operatorStartLineNumberInDiffResults) {
        this.startDiffLineNumber = startDiffLineNumber;
        this.endDiffLineNumber = endDiffLineNumber;
        this.operatorStartLineNumberInDiffResults = operatorStartLineNumberInDiffResults;
    }
    /**
     * Get the startDiffLineNumber.
     * @return the startDiffLineNumber
     */
    public int getStartDiffLineNumber() {
        return startDiffLineNumber;
    }

    /**
     * Set the startDiffLineNumber.
     * @param startDiffLineNumber the startDiffLineNumber to set
     */
    public void setStartDiffLineNumber(int startDiffLineNumber) {
        this.startDiffLineNumber = startDiffLineNumber;
    }

    /**
     * Get the endDiffLineNumber.
     * @return the endDiffLineNumber
     */
    public int getEndDiffLineNumber() {
        return endDiffLineNumber;
    }

    /**
     * Set the endDiffLineNumber.
     * @param endDiffLineNumber the endDiffLineNumber to set
     */
    public void setEndDiffLineNumber(int endDiffLineNumber) {
        this.endDiffLineNumber = endDiffLineNumber;
    }

    /**
     * Get the operatorStartLineNumberInDiffResults.
     * @return the operatorStartLineNumberInDiffResults
     */
    public int getOperatorStartLineNumberInDiffResults() {
        return operatorStartLineNumberInDiffResults;
    }

    /**
     * Set the operatorStartLineNumberInDiffResults.
     * @param operatorStartLineNumberInDiffResults the operatorStartLineNumberInDiffResults to set
     */
    public void setOperatorStartLineNumberInDiffResults(int operatorStartLineNumberInDiffResults) {
        this.operatorStartLineNumberInDiffResults = operatorStartLineNumberInDiffResults;
    }
}
