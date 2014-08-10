package mt.com.uom.project.pest.jdiff;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JDiffFileAnalyst {
public List<MethodDeclaration> javaMethods = null;
    
    /**
     * Interpret the java file.
     * @param filename Java filename
     */
    public void interpret(final String filename) {
        File javaFile = new File(filename);
        try {
            // visit the java methods
            CompilationUnit cu = JavaParser.parse(javaFile);
            JDiffFileMethodVisitor mv = new JDiffFileMethodVisitor();
            mv.visit(cu, mv);
            
            // store the java methods to the container
            javaMethods = mv.getJavaMethods();
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.toString());
        } catch (japa.parser.ParseException ex) {
        	System.out.println("ParseException: " + ex.toString());
		}
    }
    
    /**
     * Find the java function name using the start and end line numbers.
     * @param startLineNumber the startLineNumber
     * @param endLineNumber the endLineNumber
     * @return the Java functions that contain the lines
     */
    public String findFunctionName(final int startLineNumber, final int endLineNumber) {
        String functionNames = "";
        
        for (MethodDeclaration method : javaMethods) {
            if ((method.getBeginLine() <= startLineNumber) && (method.getEndLine() >= startLineNumber)) {
                functionNames += method.getName() + "," + method.getBeginLine() + "," + method.getEndLine() + ";";
            } else if ((method.getBeginLine() <= endLineNumber) && (method.getEndLine() >= endLineNumber)) {
                functionNames += method.getName() + "," + method.getBeginLine() + "," + method.getEndLine() + ";";
            } else {} // do nothing
        }
        
        return functionNames;
    }
}
