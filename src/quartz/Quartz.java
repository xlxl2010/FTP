package quartz;

import java.io.IOException;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import bussiness.BussinessUtil;

import com.util.ConfigConst;
import com.util.DateFormatTime;

import ftp.ContinueFTP;

public class Quartz implements Job
{
    
    public BussinessUtil bussinessUtil;
    
    public static void main(String[] args)
    {
        try
        {
            ConfigConst.getConfigConst();
            ContinueFTP myFtp = new ContinueFTP();
            boolean b =
                myFtp.connect(ConfigConst.ftpserver,
                    Integer.parseInt(ConfigConst.ftpport),
                    ConfigConst.ftpuser,
                    ConfigConst.ftppwd);
            System.out.println(b);
        }
        catch (NumberFormatException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (Exception e)
        {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private static final Logger logger = Logger.getLogger(Quartz.class);
    
    private static boolean isRunning = false;
    
    public void execute(JobExecutionContext context)
        throws JobExecutionException
    {
        // TODO Auto-generated method stub
        synchronized (this)
        {
            if (isRunning)
            {
                logger.info("当前job未完成");
                return;
            }
            isRunning = true;
        }
        ContinueFTP myFtp = new ContinueFTP();
        try
        {
            logger.info("文件开始下载");
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            this.bussinessUtil = (BussinessUtil)jobDataMap.get("myjob");
            myFtp.connect(ConfigConst.ftpserver,
                Integer.parseInt(ConfigConst.ftpport),
                ConfigConst.ftpuser,
                ConfigConst.ftppwd);
            String[] files = myFtp.getFileList(ConfigConst.ftpgetdir);
            String[] filenames = ConfigConst.ftpfile.split(",");
            this.downLoadAll(files, myFtp, filenames);
            
        }
        catch (Exception e)
        {
            logger.info("连接FTP出错：" + e.getMessage());
        }
        finally
        {
            try
            {
                myFtp.disconnect();
            }
            catch (IOException e)
            {
                
            }
            logger.info("文件开始下载结束");
            synchronized (this)
            {
                isRunning = false;
            }
            
        }
        
    }
    
    private void downLoadAll(String[] rfiles, ContinueFTP myFtp, String[] files)
        throws IOException
    {
        
        if (rfiles == null)
        {
            logger.info("远程文件列表为空");
            return;
        }
        
        for (int i = 0; i < rfiles.length; i++)
        {
            if (rfiles[i].startsWith(files[0]))
            {
                
                String filepath =
                    ConfigConst.ftptodir + DateFormatTime.datetoString(new GregorianCalendar(), 2) + "-" + rfiles[i];
                String result = myFtp.download(ConfigConst.ftpgetdir + rfiles[i], filepath).toString();
                logger.info("下载资源" + rfiles[i] + "文件结果：" + result);
                if (result.equals("Download_New_Success") || result.equals("Download_From_Break_Success"))
                {
                    if (bussinessUtil.getFlag())
                    {
                        bussinessUtil.setFilepath1(filepath);
                        bussinessUtil.setFilepath2("");
                        bussinessUtil.setFlag(!bussinessUtil.getFlag());
                    }
                    else
                    {
                        bussinessUtil.setFilepath1("");
                        bussinessUtil.setFilepath2(filepath);
                        bussinessUtil.setFlag(!bussinessUtil.getFlag());
                    }
                }
            }
            
        }
        
    }
    
}
