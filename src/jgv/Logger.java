package jgv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.logging.Level;

public class Logger
{
  private static final boolean LOG_TO_FILE = false;
  private static Logger _logger = new Logger();
  private Writer _writer;
  private boolean _logging = false;

  private Logger()
  {
    if (LOG_TO_FILE)
    {
      openLogFile();
    }
  }
  
  public static Logger getLogger()
  {
    return _logger;
  }
  
  public void setLogging(boolean logging)
  {
    _logging = logging;
  }

  private void openLogFile()
  {
    try
    {
      System.out.println("open file for logging...");

      String tmpDirPath = "." + File.separator + "tmp";
      File tmpDir = new File(tmpDirPath);
      if (!tmpDir.exists())
      {
        tmpDir.mkdir();
      }
      tmpDir = null;

      long id = System.currentTimeMillis();
      String logFileName = "evota_" + id + ".log";
      _writer = new FileWriter(tmpDirPath + File.separator + logFileName, true);
    }
    catch (IOException e)
    {
      throw new RuntimeException("couldn't open file for writing");
    }
  }

  public synchronized void log(Level level, String log)
  {
    if (!_logging)
    {
      return;
    }
    String newLog = "" + Calendar.getInstance().getTime().toString() 
        + " <" + level.getName() + ">: " + log;
    System.out.println(newLog);
    if (LOG_TO_FILE)
    {
      writeToLogFile(newLog);
    }
  }

  private void writeToLogFile(String log)
  {
    try
    {
      _writer.append(log + "\n");
      _writer.flush();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}
