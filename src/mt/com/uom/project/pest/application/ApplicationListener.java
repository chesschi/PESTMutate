package mt.com.uom.project.pest.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {       
    	AkkaManager.INSTANCE.initialise();        
    }

    public void contextDestroyed(ServletContextEvent sce){       
    	try {
			AkkaManager.INSTANCE.shutdown();
		} catch (InterruptedException e) {
			
		}           
    }
}
