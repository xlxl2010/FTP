package com.util;

public class ConfigConst
{
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        
    }
    
    public static String rundate;
    
    public static String ftpserver;
    
    public static String ftpuser;
    
    public static String ftppwd;
    
    public static String ftpport;
    
    public static String ftpgetdir;
    
    public static String ftptodir;
    
    public static String ftpfile;
    
    public static String ftpfileend;
    
    public static void getConfigConst()
        throws Exception
    {
        
        rundate = ConfigManager.getInstance().getConfigItem("rundate");
        ftpserver = ConfigManager.getInstance().getConfigItem("ftpserver");
        ftpuser = ConfigManager.getInstance().getConfigItem("ftpuser");
        ftppwd = ConfigManager.getInstance().getConfigItem("ftppwd");
        ftpport = ConfigManager.getInstance().getConfigItem("ftpport");
        ftpgetdir = ConfigManager.getInstance().getConfigItem("ftpgetdir");
        ftptodir = ConfigManager.getInstance().getConfigItem("ftptodir");
        ftpfile = ConfigManager.getInstance().getConfigItem("ftpfile");
        ftpfileend = ConfigManager.getInstance().getConfigItem("ftpfileend");
    }
}
