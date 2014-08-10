package mt.com.uom.project.pest.jdiff;

import java.util.ArrayList;
import java.util.List;

public class JDiffInterpreter {
	public static final char NULL_OPERATOR = ' ';
    public static final char INSERT_OPERATOR = '+';
    public static final char REMOVE_OPERATOR = '-';
    public static final char AT_OPERATOR = '@';
    
    private final List<JDiffDifferences> diffObjects = new ArrayList<>();
    public char diffOperator;
    private int curDiffStartLineNumber;
    private int curDiffNumberOfLines;
    private int curDiffLineCount;
    private int curDiffOperatorStartLineNumberInDiffResults;
    private int operatorStartLineNumber;
    private boolean operatorAppeared;
    private String filename;
    
    /**
     * Explicit constructor.
     * @param diffOperator The diffOperator
     */
    public JDiffInterpreter(final char diffOperator) {
        this.diffOperator = diffOperator;
        this.curDiffStartLineNumber = 0;
        this.curDiffNumberOfLines = 0;
        this.curDiffLineCount = 0;
        this.curDiffOperatorStartLineNumberInDiffResults = 0;
        this.operatorStartLineNumber = 0;
        this.operatorAppeared = false;
    }
    
    /**
     * Set filename.
     * @param filename The filename
     */
    public void setFilename(final String filename) {
        this.filename = filename;
    }
    
    /**
     * Get filename.
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }
    
    /**
     * Update the diff parameters obtained from the at operator.
     * @param curDiffStartLineNumber The curDiffStartLineNumber to set
     * @param curDiffNumberOfLines The curDiffNumberOfLines to set
     */
    public void updateDiffParameters(final int curDiffStartLineNumber, final int curDiffNumberOfLines) {
        // update the current diff start line number and number of lines
        this.curDiffStartLineNumber = curDiffStartLineNumber;
        this.curDiffNumberOfLines = curDiffNumberOfLines;
        
        // reset the current line count and operator start line number
        curDiffLineCount = 0;
        operatorStartLineNumber = 0;
    }

    /**
     * Interpret the line.
     * @param line The line to interpret
     * @param index The index
     * @return true if it interprets okay.
     */
    public boolean interpretLine(final String line, final int index) {
        boolean interpretOk = false;
        char operator = NULL_OPERATOR;
        
        // obtain the operator from the line
        if (0 < line.length()) {
            operator = line.charAt(0);
        } else {} // do nothing
        
        if (0 == curDiffNumberOfLines) {
            // if the number of diff lines is zero, it will not contain any diff operator
            // so no interpret is required
            interpretOk = true;
        } else if (curDiffLineCount < curDiffNumberOfLines) {
            // the line count should not exceed the number of diff lines reported by the diff results
            interpretOk = true;
            
            if (operator == diffOperator) {
                // if the line contains the diff operator
                // assign the line number that first sees the operator
                if (!operatorAppeared) {
                    operatorStartLineNumber = curDiffStartLineNumber + curDiffLineCount;
                    curDiffOperatorStartLineNumberInDiffResults = index;
                } else {} // do nothing
                
                // increase the line count
                curDiffLineCount++;
                
                // update the operator appeared flag
                operatorAppeared = (operator == diffOperator);
            } else {
                // create the diff object if the operator has been appeared and reached to the end (i.e. non-diff operator)
                if (operatorAppeared) {
                    diffObjects.add(new JDiffDifferences(operatorStartLineNumber, curDiffStartLineNumber + curDiffLineCount - 1, curDiffOperatorStartLineNumberInDiffResults));
                    operatorAppeared = false;
                } else {} // do nothing
                
                // increase the diff line count for null operator
                if (operator == NULL_OPERATOR) {
                    curDiffLineCount++;
                } else {} // do nothing
            }
        } else {} // do nothing
        
        return interpretOk;
    }
    
    /**
     * Interpret end of line.
     */
    public void interpretEndOfLine() {
        // create the diff object if the operator has been appeared and reached end of file
        if (operatorAppeared) {
            JDiffDifferences diffObject = new JDiffDifferences(operatorStartLineNumber, curDiffStartLineNumber + curDiffLineCount, curDiffOperatorStartLineNumberInDiffResults);
            diffObjects.add(diffObject);
            operatorAppeared = false;
        } else {} // do nothing
    }
    
    /**
     * Get the diffObjects.
     * @return the diffObjects.
     */
    public List<JDiffDifferences> getDiffObjects() {
        return diffObjects;
    }
}
