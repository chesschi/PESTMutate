package mt.com.uom.project.pest.jdiff;

import org.apache.commons.lang3.StringUtils;

public class JDiffParser {
	private static final String DIFF_COMMAND_JAVA_REGEX = "^diff --git a/\\w+\\.(java|cpp|h) b/\\w+\\.(java|cpp|h)$";
    private static final String DIFF_LINE_REGEX = "^@@ -\\d+,\\d+ \\+\\d+,\\d+ @@.*";
    private static final String REMOVE_OPERATOR_LINE_REGEX = "^---.+";
    private static final String DEV_NULL_FILENAME = "/dev/null";
    private static final char FILENAME1_OPERATOR = '-';
    private static final char FILENAME2_OPERATOR = '+';
    private static final char AT_OPERATOR = '@';
    private final String[] diffResults;
    private final JDiffInterpreter removeDiffInterpreter = new JDiffInterpreter(JDiffInterpreter.REMOVE_OPERATOR);
    private final JDiffInterpreter insertDiffInterpreter = new JDiffInterpreter(JDiffInterpreter.INSERT_OPERATOR);
    
    private enum DiffResultsParserState {
        StateParseDiffCommand,
        StateParseFilename1,
        StateParseFilename2,
        StateParseDiffAt,
        StateParseFilename1Diff,
        StateParseFilename2Diff,
        StateParseProcessDiffLines
    }
    
    JDiffParser(String[] diffResults) {
        this.diffResults = diffResults;
    }
    
    public boolean interpretDiffResults() {
        
        DiffResultsParserState state = DiffResultsParserState.StateParseDiffCommand;
        boolean ok = (1 <= diffResults.length), finish = false;
        int index = 0;
        
        // parse the diff results
        while (ok && (!finish)) {
            switch (state) {
                case StateParseDiffCommand: {
                    // check first line: diff --git <filename1> <filename2>
                    if (diffResults[0].matches(DIFF_COMMAND_JAVA_REGEX)) {
                        String[] filenames = diffResults[0].split(" ");
                        removeDiffInterpreter.setFilename(filenames[2].substring(2));
                        insertDiffInterpreter.setFilename(filenames[3].substring(2));    
                        index++;
                        state = DiffResultsParserState.StateParseFilename1;
                    } else {
                        ok = false;
                    }
                    break;
                }
                case StateParseFilename1: {
                    if (diffResults[index].matches(REMOVE_OPERATOR_LINE_REGEX)) {
                        // check remove line: --- <filename1> or /dev/null
                        ok = ((diffResults[index].equals(StringUtils.repeat(FILENAME1_OPERATOR, 3) + " a/" + removeDiffInterpreter.getFilename())) ||
                              (diffResults[index].equals(StringUtils.repeat(FILENAME1_OPERATOR, 3) + " " + DEV_NULL_FILENAME)));
                        state = DiffResultsParserState.StateParseFilename2;
                    } else {} // do nothing
                    
                    index++;
                    break;
                }
                case StateParseFilename2: {
                    // check insert line: +++ <filename2> or /dev/null
                    ok = ((diffResults[index].equals(StringUtils.repeat(FILENAME2_OPERATOR, 3) + " b/" + insertDiffInterpreter.getFilename())) ||
                          (diffResults[index].equals(StringUtils.repeat(FILENAME2_OPERATOR, 3) + " " + DEV_NULL_FILENAME)));
                    index++;
                    state = DiffResultsParserState.StateParseDiffAt;
                    break;
                }
                case StateParseDiffAt: {
                    // check at line: @@ -xx,xx +xx,xx @@
                    if (diffResults[index].matches(DIFF_LINE_REGEX)) {
                        String[] lineNumbers = diffResults[index].split(" |,");
                        removeDiffInterpreter.updateDiffParameters(Integer.parseInt(lineNumbers[1].substring(1)), Integer.parseInt(lineNumbers[2]));
                        insertDiffInterpreter.updateDiffParameters(Integer.parseInt(lineNumbers[3].substring(1)), Integer.parseInt(lineNumbers[4]));
                        index++;
                        state = DiffResultsParserState.StateParseProcessDiffLines;
                    } else {
                        ok = false;
                    }
                    break;
                }
                case StateParseProcessDiffLines: {
                    if (index < diffResults.length) {
                        if ((0 < diffResults[index].length()) && (diffResults[index].charAt(0) == AT_OPERATOR)) {
                            state = DiffResultsParserState.StateParseDiffAt;
                        } else {
                            boolean diff1Ok = removeDiffInterpreter.interpretLine(diffResults[index], index);
                            boolean diff2Ok = insertDiffInterpreter.interpretLine(diffResults[index], index);
                            ok = (diff1Ok && diff2Ok);
                            index++;
                        }
                    } else {
                        removeDiffInterpreter.interpretEndOfLine();
                        insertDiffInterpreter.interpretEndOfLine();
                        finish = true;
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
        
        return ok;
    }
    
    public String updateDiffResultsWithFunctionName(final String oldDir, final String newDir) {
        String[] diffResultsWithFunctonNames = diffResults;
        String javaFileName = removeDiffInterpreter.getFilename();
        
        // TODO: should download previous and current java files and
        // compare the diff objects against these files
        JDiffFileAnalyst oldJavaInterpreter = new JDiffFileAnalyst();
        oldJavaInterpreter.interpret(oldDir + "/" + javaFileName);
        
        for (JDiffDifferences removeDiffObject : removeDiffInterpreter.getDiffObjects()) {
            String functionName = oldJavaInterpreter.findFunctionName(removeDiffObject.getStartDiffLineNumber(), removeDiffObject.getEndDiffLineNumber());
            int diffResultsLineNumber = removeDiffObject.getOperatorStartLineNumberInDiffResults();
            diffResultsWithFunctonNames[diffResultsLineNumber] = functionName + "\n" + diffResultsWithFunctonNames[diffResultsLineNumber];
        }
        
        JDiffFileAnalyst newJavaInterpreter = new JDiffFileAnalyst();
        newJavaInterpreter.interpret(newDir + "/" + javaFileName);
        
        for (JDiffDifferences insertDiffObject : insertDiffInterpreter.getDiffObjects()) {
            String functionName = newJavaInterpreter.findFunctionName(insertDiffObject.getStartDiffLineNumber(), insertDiffObject.getEndDiffLineNumber());
            int diffResultsLineNumber = insertDiffObject.getOperatorStartLineNumberInDiffResults();
            diffResultsWithFunctonNames[diffResultsLineNumber] = functionName + "\n" + diffResultsWithFunctonNames[diffResultsLineNumber];
        }
        
        return StringUtils.join(diffResultsWithFunctonNames, "\n");
    }
}
