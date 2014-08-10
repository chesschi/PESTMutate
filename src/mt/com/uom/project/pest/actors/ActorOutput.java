package mt.com.uom.project.pest.actors;

import mt.com.uom.project.pest.configuration.Configuration;
import mt.com.uom.project.pest.files.FileManagement;
import mt.com.uom.project.pest.messages.ResultDiffFunctions;
import mt.com.uom.project.pest.messages.ResultVersions;
import akka.actor.UntypedActor;

public class ActorOutput extends UntypedActor {

	final String NAME;
	public ActorOutput(String name) {
		this.NAME = name;
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof ResultDiffFunctions) {
			ResultDiffFunctions message = (ResultDiffFunctions)msg;				
			FileManagement.INSTANCE.writeObjectToFile(msg, Configuration.getDiffFunctionResultFilePath(message.getRequest().getReceiptId()));
		}
		else if (msg instanceof ResultVersions) {		
			ResultVersions message = (ResultVersions)msg;				
			FileManagement.INSTANCE.writeObjectToFile(msg, Configuration.getVersionResultFilePath(message.getRequest().getReceiptId()));			
		}
		else {
			unhandled(msg);
		}	
	}

}
