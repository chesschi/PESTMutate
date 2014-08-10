package mt.com.uom.project.pest.jdiff;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class JDiffFileMethodVisitor extends VoidVisitorAdapter<Object> {
	private final List<MethodDeclaration> javaMethods = new ArrayList<>();
    
    /**
     * Override visit function.
     * @param m Method declaration object
     * @param arg Object argument
     */
    @Override
    public void visit(MethodDeclaration m, Object arg) {
        javaMethods.add(m);
    }
    
    /**
     * Get java methods.
     * @return a list of java methods
     */
    public List<MethodDeclaration> getJavaMethods() {
        return javaMethods;
    }
}
