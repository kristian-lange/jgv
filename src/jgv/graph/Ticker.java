package jgv.graph;

import jgv.interfaces.ITickable;
import jgv.item.JGVItem;
import jgv.item.moduls.Module;

/**
 * @author Kristian Lange
 *
 */
public class Ticker
{

  private static final int MAX_MEMBERS = 10;
  
  private ITickable[] _tickables = new ITickable[MAX_MEMBERS]; 
  
  
  /**
   * Adds 'tickables', classes with a tick() method. The order you do this is
   * important. In this order they will be ticked every graphic's frame. 
   * @param tickable
   * @return
   */
  public boolean add(ITickable tickable)
  {
    for (int i = 0; i < MAX_MEMBERS; i++)
    {
      if (_tickables[i] == null)
      {
        _tickables[i] = tickable;
        return true;
      }
    }
    return false;
  }
  
  public void tick(float frameTime)
  {
    for (ITickable tickable : _tickables)
    {
      if (tickable == null)
      {
        break;
      }
      tickable.tick(frameTime);
    }
  }

  /**
   * Tick everything in a JGVItem. 
   * @param frameTime
   * @param item
   */
  public void tickItem(float frameTime, JGVItem<?, ?> item)
  {
  	if (item instanceof ITickable)
    {
      ((ITickable)item).tick(frameTime);
    }
  	if (item.getItemGraphics() instanceof ITickable)
    {
      ((ITickable)item.getItemGraphics()).tick(frameTime);
    }
  	if (item.getItemPhysics() instanceof ITickable)
    {
      ((ITickable)item.getItemPhysics()).tick(frameTime);
    }
  	
    for (Module module : item.getModuls())
    {
      if (module == null)
      {
        break;
      }
      if (module instanceof ITickable)
      {
        ((ITickable)module).tick(frameTime);
      }
    }
  }

}
