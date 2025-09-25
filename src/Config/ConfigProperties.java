package Config;


import java.io.InputStream;

public class ConfigProperties 
{
	private String path = "";
	private java.util.regex.Pattern section  = null;
	private java.util.regex.Pattern keyValue = null;
	private java.util.TreeMap<String, java.util.Map<String, String>> entries = null;

	public ConfigProperties(String path)
	{
		this.path = path;
		this.section = java.util.regex.Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
		this.keyValue = java.util.regex.Pattern.compile("\\s*([^=]*)=(.*)");
		this.entries = new java.util.TreeMap<String, java.util.Map<String, String>>();
	}

	public boolean loadConfig() 
	{
		java.io.BufferedReader tBR = null;
		try
		{ 
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
			//tBR = new java.io.BufferedReader(new java.io.FileReader(this.path));
			tBR = new java.io.BufferedReader(new java.io.InputStreamReader(is));
		}
		catch(Exception ex)
		{ return false; }
		
		String tLine;
		String tSection = null;
		try 
		{
			while ((tLine = tBR.readLine()) != null)
			{
				java.util.regex.Matcher tMatcher = this.section.matcher(tLine);
			    if (tMatcher.matches()) 
			    { tSection = tMatcher.group(1).trim(); }
			    else if (tSection != null)
			    {
			    	tMatcher = this.keyValue.matcher(tLine);
			    	if (tMatcher.matches()) 
			    	{
			    		String tKey   = tMatcher.group(1).trim();
			    		String tValue = tMatcher.group(2).trim();
			    		java.util.Map<String, String> tKV = this.entries.get(tSection);
			    		if (tKV == null) 
			    		{ this.entries.put( tSection, tKV = new java.util.TreeMap<>()); }
			    		tKV.put(tKey, tValue);
			    	}
			    }
			}
			
			return true;
		} 
		catch (java.io.IOException e) 
		{ return false; }
		finally
		{
			try 
			{ tBR.close(); } 
			catch (java.io.IOException e) 
			{ }	
		}
	}

	public String getString(String section, String key, String defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if ( tKV == null ) 
		{ return defaultValue; }
		
		try
		{ return tKV.get(key); }
		catch(Exception ex)
		{ return defaultValue; }
	}
	
	public short getShort(String section, String key, short defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if( tKV == null ) 
		{ return defaultValue; }
		
		try
		{ return Short.parseShort(tKV.get(key)); }
		catch(java.lang.NumberFormatException ex)
		{ return defaultValue; }
	}

	public int getInt(String section, String key, int defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if( tKV == null ) 
		{ return defaultValue; }

		try
		{ return Integer.parseInt(tKV.get(key)); }
		catch(java.lang.NumberFormatException ex)
		{ return defaultValue; }
	}
	
	public long getLong(String section, String key, long defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if( tKV == null ) 
		{ return defaultValue; }

		try
		{ return Long.parseLong(tKV.get(key)); }
		catch(java.lang.NumberFormatException ex)
		{ return defaultValue; }
	}

	public float getFloat(String section, String key, float defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if( tKV == null ) 
		{ return defaultValue; }

		try
		{ return Float.parseFloat(tKV.get(key)); }
		catch(java.lang.NumberFormatException ex)
		{ return defaultValue; }
	}

	public double getDouble(String section, String key, double defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if( tKV == null ) 
		{ return defaultValue; }

		try
		{ return Double.parseDouble(tKV.get(key)); }
		catch(java.lang.NumberFormatException ex)
		{ return defaultValue; }
	}
	
	public boolean getBoolean(String section, String key, boolean defaultValue) 
	{
		java.util.Map<String, String> tKV = this.entries.get(section);
		if( tKV == null ) 
		{ return defaultValue; }

		try
		{ return Boolean.parseBoolean(tKV.get(key)); }
		catch(Exception ex)
		{ return defaultValue; }
	}
	
	public java.util.Map<String, String> GetSection(String section)
	{
		if (this.entries.containsKey(section) == true)
		{ return this.entries.get(section); }
		else
		{ return null; }
	}
}
