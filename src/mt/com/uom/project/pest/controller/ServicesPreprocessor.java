package mt.com.uom.project.pest.controller;

import java.io.IOException;

import mt.com.uom.project.pest.application.AkkaManager;
import mt.com.uom.project.pest.configuration.Configuration;
import mt.com.uom.project.pest.files.FileManagement;
import mt.com.uom.project.pest.messages.RequestDiffFunctions;
import mt.com.uom.project.pest.messages.RequestReceipt;
import mt.com.uom.project.pest.messages.RequestVersions;
import mt.com.uom.project.pest.messages.ResultDiffFunctions;
import mt.com.uom.project.pest.messages.ResultVersions;
import mt.com.uom.project.pest.names.NamesService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(NamesService.PREPROCESSOR)
public class ServicesPreprocessor {

	@RequestMapping(value ={NamesService.RequestVersions}, headers="Accept=application/json", method = RequestMethod.POST)
    public @ResponseBody RequestReceipt requestVersions(@RequestBody RequestVersions data) {
		data.setReceiptId(++NamesService.ReceiptID);
		AkkaManager.INSTANCE.receiveRequest(data);
		RequestReceipt receipt = new RequestReceipt();
		receipt.setReceiptId(data.getReceiptId());
		return receipt;
    }	
	
	@RequestMapping(value ={NamesService.RequestDiffFunctions}, headers="Accept=application/json", method = RequestMethod.POST)
    public @ResponseBody RequestReceipt requestDiffFunctions(@RequestBody RequestDiffFunctions data) {
		data.setReceiptId(++NamesService.ReceiptID);
		AkkaManager.INSTANCE.receiveRequest(data);	
		RequestReceipt receipt = new RequestReceipt();
		receipt.setReceiptId(data.getReceiptId());
		return receipt;
    }	
	
	@RequestMapping(value ={NamesService.RetrieveVersions}, headers="Accept=application/json", method = RequestMethod.POST)
    public @ResponseBody ResultVersions retrieveVersions(@RequestBody RequestReceipt data) {
		
		ResultVersions result = new ResultVersions();
		
		if (FileManagement.INSTANCE.fileExists(Configuration.getVersionResultFilePath(data.getReceiptId()))) {
			try {
				result = (ResultVersions)FileManagement.INSTANCE.readObjectFromFile(Configuration.getVersionResultFilePath(data.getReceiptId()));
			} catch (IOException e) {
				
			}
		}
		
		return result;
    }	

	@RequestMapping(value ={NamesService.RetrieveDiffFunctions}, headers="Accept=application/json", method = RequestMethod.POST)
    public @ResponseBody ResultDiffFunctions retrieveDiffFunctions(@RequestBody RequestReceipt data) {
		
		ResultDiffFunctions result = new ResultDiffFunctions();
		
		if (FileManagement.INSTANCE.fileExists(Configuration.getDiffFunctionResultFilePath(data.getReceiptId()))) {
			try {
				result = (ResultDiffFunctions)FileManagement.INSTANCE.readObjectFromFile(Configuration.getDiffFunctionResultFilePath(data.getReceiptId()));
			} catch (IOException e) {
				
			}
		} 
		
		return result;
    }
}
