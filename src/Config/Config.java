package Config;

import java.util.List;

import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {

	private Logger logger = null;

	private static volatile Config instance = null;
	private FileBasedConfiguration configuration = null;
	
	public Config() {
		this.logger = LogManager.getLogger(Config.class);
		

		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class);
		
		builder.configure(params.properties()
				.setFileName("application.properties")
				.setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
        try {
            configuration = builder.getConfiguration();
        } catch (ConfigurationException ex) {
         
            System.exit(2);
        }
	}
	
	public static Config getInstance() {
		if (instance == null) {
			synchronized (Config.class) {
				if (instance == null) {
					instance = new Config();
				}
			}
		}
		
		return instance;
	}
	
	public boolean validate() {
		if (this.getString("auth.issuer") == null) {
			this.logger.error("Config auth.issuer is null");
			return false;
		}
		
		if (this.getString("auth.audience") == null) {
			this.logger.error("Config auth.audience is null");
			return false;
		}
		
				
		return true;
	}
	
	public String getString(String key) {
		try {
			return this.configuration.getString(key, null);
		} catch (Exception ex) {
			
			return null;
		}
    }
	
	public Integer getInteger(String key) {
		try {
			return this.configuration.getInteger(key, null);
		} catch (Exception ex) {
			
			return null;
		}
    }
	
	public Long getLong(String key) {
		try {
			return this.configuration.getLong(key, null);
		} catch (Exception ex) {
		
			return null;
		}
    }
	
	public Boolean getBoolean(String key) {
		try {
			return this.configuration.getBoolean(key, null);
		} catch (Exception ex) {
			
			return null;
		}
	}
	
	public <T> List<T> getArray(Class<T> cls, String key) {
		try {
			return this.configuration.getList(cls, key, null);
		} catch (Exception ex) {
			
			return null;
		}
	}
	
}
