package mt.com.uom.project.pest.configuration;

public class Configuration {
	public static final String TempFolder="/var/tmp";
	public static final String GitFolderPrefix="/jgit";
	public static final String TempFolderPrefix="/jtgit";
	public static final String VersionsFile="/allversions.txt";
	public static final String DiffFunctionsFile="/alldifffunctions.txt";
	
	public static final String getLocalGitRepository(int id) {
		return TempFolder+GitFolderPrefix+"_"+id;
	}
	
	public static final String getLocalTempFolder(int id) {
		return TempFolder+TempFolderPrefix+"_"+id;
	}
	public static final String getVersionResultFilePath(int id) {
		return TempFolder+GitFolderPrefix+"_"+id+VersionsFile;
	}
	
	public static final String getDiffFunctionResultFilePath(int id) {
		return TempFolder+GitFolderPrefix+"_"+id+DiffFunctionsFile;
	}
}
