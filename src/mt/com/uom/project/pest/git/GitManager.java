package mt.com.uom.project.pest.git;

import java.util.List;

import mt.com.uom.project.pest.configuration.Configuration;
import mt.com.uom.project.pest.jdiff.JDiffGitEngine;
import mt.com.uom.project.pest.messages.RequestDiffFunctions;
import mt.com.uom.project.pest.messages.RequestVersions;
import mt.com.uom.project.pest.messages.ResultDiffFunctions;
import mt.com.uom.project.pest.messages.ResultVersions;

public enum GitManager {
	
	INSTANCE;
	
	public ResultVersions getVersions(RequestVersions request) {		
		ResultVersions versions = new ResultVersions();
		JDiffGitEngine engine = downloadRepository(request);
		versions.setRequest(request);
		versions.setCommitObjects(engine.getRevisionLogs(Configuration.getLocalGitRepository(request.getReceiptId())));
		return versions;
	}
	
	public ResultDiffFunctions getDiffFunctions(RequestDiffFunctions request) {		
		ResultDiffFunctions diffFunctions = new ResultDiffFunctions();
		RequestVersions requestVersion = new RequestVersions();
		requestVersion.setReceiptId(request.getReceiptId());
		requestVersion.setGitRepositoryLocation(request.getGitRepositoryLocation());
		requestVersion.setGitBranch(request.getGitBranch());
		requestVersion.setUsername(request.getUsername());
		requestVersion.setPassword(request.getPassword());
		JDiffGitEngine engine = downloadRepository(requestVersion);
		List<String> functions = engine.compare(Configuration.getLocalGitRepository(request.getReceiptId()), 
												Configuration.getLocalTempFolder(request.getReceiptId()), 
												request.getVersion2(), 
												request.getVersion1());
		diffFunctions.setRequest(request);
		diffFunctions.setDiffObjects(functions);		
		return diffFunctions;
	}
	
	private JDiffGitEngine downloadRepository(RequestVersions request) {
		JDiffGitEngine engine = new JDiffGitEngine(request.getGitRepositoryLocation(),
												   request.getGitBranch(),
												   request.getUsername(),
												   request.getPassword());
		engine.download(Configuration.getLocalGitRepository(request.getReceiptId()));
		return engine;
	}
	
	
}
