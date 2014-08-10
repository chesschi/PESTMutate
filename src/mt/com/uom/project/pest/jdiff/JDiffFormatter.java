package mt.com.uom.project.pest.jdiff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;

public class JDiffFormatter extends DiffFormatter {
private final ByteArrayOutputStream outputStream;
    
    public JDiffFormatter(ByteArrayOutputStream out) {
        super(out);
        this.outputStream = out;
    }
    
    public void format(DiffEntry ent, String oldDir, String newDir) throws IOException {
        // format the diff results using the default settings
        super.format(ent);
        
        // obtain the diff results with the default format function
        String diffResultsWithFunctionName = outputStream.toString("UTF-8");
        String[] diffResults = outputStream.toString().split("\n");
        
        // parse the diff results and add function names
        JDiffParser parser = new JDiffParser(diffResults);
        if (parser.interpretDiffResults()) {
            diffResultsWithFunctionName = parser.updateDiffResultsWithFunctionName(oldDir, newDir);
        } else {} // do nothing
        
        // write the diff results with function name to output stream
        outputStream.reset();
        outputStream.flush();
        outputStream.write(diffResultsWithFunctionName.getBytes()); 
    }
}
