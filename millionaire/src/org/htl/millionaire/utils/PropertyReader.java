package org.htl.millionaire.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author albert
 * Helper class to read java property files from classpath
 */
public class PropertyReader {
	private Properties p;

	public PropertyReader(String path) throws IOException {
		p = new Properties();
		p.load(getClass().getClassLoader().getResourceAsStream(path));
	}
	
	public String get(String key)
	{
		return p.getProperty(key);
	}
}
