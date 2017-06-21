package jgv.graphics;

import jgv.interfaces.IStatusTextable;


public class StatusTexter
{
  
  private static final int MAX_MEMBERS = 10;
  
  private IStatusTextable[] _statusTexterMembers =
      new IStatusTextable[MAX_MEMBERS]; 

  public boolean addStatusTextable(IStatusTextable statusTextable)
  {
    for (int i = 0; i < MAX_MEMBERS; i++)
    {
      if (_statusTexterMembers[i] == null)
      {
        _statusTexterMembers[i] = statusTextable;
        return true;
      }
    }
    return false;
  }
  
  public String getStatusText()
  {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < MAX_MEMBERS; i++)
    {
      if (_statusTexterMembers[i] != null)
      {
        builder.append(_statusTexterMembers[i].getStatusText());
      }
      else
      {
        break;
      }
    }
    return builder.toString();
  }
  
}
