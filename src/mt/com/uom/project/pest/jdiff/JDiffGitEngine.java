package mt.com.uom.project.pest.jdiff;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class JDiffGitEngine {
	private final String remoteGitUrl;
    private final String gitBranch;
    private final String gitUsername;
    private final String gitPassword;
    
    /**
     * Explicit constructor.
     * @param remoteGitUrl Git URL
     * @param gitBranch Git branch
     * @param gitUsername Git account username
     * @param gitPassword Git account password
     */
    public JDiffGitEngine(final String remoteGitUrl, final String gitBranch, final String gitUsername, final String gitPassword) {
        this.remoteGitUrl = remoteGitUrl;
        this.gitBranch = gitBranch;
        this.gitUsername = gitUsername;
        this.gitPassword = gitPassword;
    }
    
    /**
     * Download the files from the remote git repository to local directory.
     * @param gitDir Local directory to store the files
     */
    public void download(final String gitDir)
    {
        try {
            // set the URL for the clone command
            CloneCommand command = Git.cloneRepository().setURI(remoteGitUrl);
            
            // set local directory 
            if (gitDir != null) {
                // create the local directory
                File gitWorkDir = new File(gitDir);
                gitWorkDir.mkdir();
                command.setDirectory(gitWorkDir);
            } else {} // do nothing
            
            // set branch
            if (gitBranch != null) {
                command.setBranch(gitBranch);
            } else {} // do nothing
            
            // set credentials
            if ((gitUsername != null) && (gitPassword != null)) {
                command.setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitUsername, gitPassword));
            }
            else {} // do nothing
            
            // execute the clone command
            command.call();
        } catch (GitAPIException ex) {
            System.out.println("GitAPIException: " + ex.toString());
        } catch (JGitInternalException ex) {
            System.out.println("JGitInternalException: " + ex.toString());
        }
    }
    
    /**
     * Get revision logs.
     * @param gitDir The local git directory
     * @return a list of commit objects
     */
    public List<JDiffCommitted> getRevisionLogs(String gitDir) {
        List<JDiffCommitted> revisions = new ArrayList<>();
        
        try {
            // open the git directory
            File gitWorkDir = new File(gitDir + "/.git");
            Git git = Git.open(gitWorkDir);
            
            // get the git logs (i.e. show all the historys) for previous commits
            // equivalent to command <git log>
            Iterable<RevCommit> logs = git.log().call();
            Iterator<RevCommit> logsItr = logs.iterator();
            while (logsItr.hasNext()) {
                RevCommit commit = logsItr.next();
                JDiffCommitted commitObject = new JDiffCommitted(
                        commit.getName(),
                        (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date(commit.getCommitTime() * 1000L)),
                        commit.getShortMessage(),
                        commit.getFullMessage());
                revisions.add(commitObject);
            }            
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.toString());
        } catch (GitAPIException ex) {
            System.out.println("GitAPIException: " + ex.toString());
        }
         
        return revisions;
    }
    
    /**
     * Compare the old revision and new revision
     * and generate the git diff with function names.
     * @param gitDir The directory contains the git files
     * @param tmpDir The temporary directory to store two revision files
     * @param oldRevision Old revision name
     * @param newRevision New revision name
     */
    public List<String> compare(final String gitDir, final String tmpDir, final String oldRevision, final String newRevision)
    {
    	List<String> result = new ArrayList<String>();
    	
        try {
            String oldDir = tmpDir + "/old";
            String newDir = tmpDir + "/new";
            
            // TODO: remove oldDir and newDir, same for clone?
            
            // download the latest commit and reset to old revision
            // equivalent to command <git reset --hard $SHA1>
            download(oldDir);
            Git.open(new File(oldDir)).reset().setMode(ResetCommand.ResetType.HARD).setRef(oldRevision).call();
            
            // download the latest commit and reset to new revision
            // equivalent to command <git reset --hard $SHA1>
            download(newDir);
            Git.open(new File(newDir)).reset().setMode(ResetCommand.ResetType.HARD).setRef(newRevision).call();
            
            // open the git working directroy
            File gitWorkDir = new File(gitDir);
            Git git = Git.open(gitWorkDir);
            
            // generate diff based on the old and new revisions
            ObjectId oldId = git.getRepository().resolve(oldRevision + "^{tree}");
            ObjectId newId = git.getRepository().resolve(newRevision + "^{tree}");

            ObjectReader reader = git.getRepository().newObjectReader();

            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            oldTreeIter.reset(reader, oldId);
            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            newTreeIter.reset(reader, newId);

            List<DiffEntry> diffs= git.diff()
                    .setNewTree(newTreeIter)
                    .setOldTree(oldTreeIter)
                    .call();

            // use java formatter to format the diff results with java function
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JDiffFormatter df = new JDiffFormatter(out);
            df.setRepository(git.getRepository());

            for(DiffEntry diff : diffs)
            {
              df.format(diff, oldDir, newDir);
              String diffText = out.toString("UTF-8");
              System.out.println(diffText);
              result.add(diffText);
              out.reset();
            }
        } catch (GitAPIException ex) {
            System.out.println("GitAPIException: " + ex.toString());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.toString());
        }
        
        return result;
    }
}
