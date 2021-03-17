package beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

@Startup
@Singleton
public class Log4JLogger {
	
	private Logger logger;

	@PostConstruct
	public void init() {
		logger = Logger.getLogger(Log4JLogger.class);
		try {
			logger.addAppender(new FileAppender(new PatternLayout("[%d][%5p] [%C] %x - %m%n"),
					"C:\\Users\\Tiziano Massa\\workspace-uno\\exomusic-WEB\\WebContent\\WEB-INF\\log\\application.log"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	

}
