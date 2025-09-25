package API;


import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.properties.PropertiesConfigurationFactory;
import Config.ConfigProperties;


public class ContextListener implements ServletContextListener {

	public static Logger logger = null;
	public static ConfigProperties config = null;
	
	public static PrivateKey privateKey = null;
	public static RSAPublicKey publicKey = null;
	
	public ContextListener() {
		if (logger == null) {
			ConfigurationSource source = ConfigurationSource.fromResource("log4j2.properties", this.getClass().getClassLoader());
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			Configuration config = PropertiesConfigurationFactory.getInstance().getConfiguration(context, source);
			context.setConfiguration(config);
			
			logger = LogManager.getLogger(ContextListener.class);
		}
		
		/*
		if (config == null) {
			logger.info("Initializing configurations");
			config = new ConfigProperties("application.properties");
			if (config.loadConfig() == false) {
				logger.fatal("Unable to load config file");
			}
		}
		*/
		
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("Context initialized");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("Context destroyed");
	}
	

}
