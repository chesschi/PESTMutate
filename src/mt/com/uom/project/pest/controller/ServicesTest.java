package mt.com.uom.project.pest.controller;

import java.util.ArrayList;
import java.util.List;

import mt.com.uom.project.pest.messages.MessageTesting;
import mt.com.uom.project.pest.names.NamesService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(NamesService.TESTING)
public class ServicesTest {

	@RequestMapping(value ={NamesService.Testing}, headers="Accept=application/json", method = RequestMethod.POST)
    public @ResponseBody MessageTesting getTestingMessage() {
        MessageTesting result = new MessageTesting();
        
        result.setTestingInteger(1);
        result.setTestingString("Hello");
        List<String> strings = new ArrayList<String>();
        strings.add("Hello");
        strings.add("World");
        result.setTestingStrings(strings);
        
        return result;
    }
}
