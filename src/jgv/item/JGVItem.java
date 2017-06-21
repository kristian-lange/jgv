package jgv.item;

import java.util.Random;

import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;
import jgv.item.moduls.Module;

public abstract class JGVItem <T extends JGVItemGraphics, U extends JGVItemPhysics>
    implements IBounceable, ITickable, IItemForItemGraphics<T, U>,
    IItemForItemPhysics<T, U>
{
  
  public static final Random RANDOM = new Random();
  public static final int MAX_MODULES = 10;
  
  private Module[] _modules = new Module[MAX_MODULES];
  private T _itemGraphics;
  private U _itemPhysics;
  private final int _id;
  private String _label = "";
  private String _comment = "";
  private boolean _retired = false;
  protected int _timeAfterLastBounce = 0;
  protected int _maxIdleTime;
  
  public JGVItem(int id, T itemGraphics, U itemPhysics)
  {
    _id = id;
    _itemGraphics = itemGraphics;
    _itemPhysics = itemPhysics;
  }
  
  public Module[] getModuls()
  {
    return _modules;
  }
  
  public boolean addModul(Module module)
  {
    for (int i = 0; i < MAX_MODULES; i++)
    {
      if (_modules[i] == module)
      {
        return false;
      }
      if (_modules[i] == null)
      {
        _modules[i] = module;
        module.setItem(this);
        module.setup();
        return true;
      }
    }
    return false;
  }
  
  public T getItemGraphics()
  {
    return _itemGraphics;
  }
  
  protected void changeItemGraphics(T newItemGraphics)
  {
    _itemGraphics = newItemGraphics;
  }
  
  public U getItemPhysics()
  {
    return _itemPhysics;
  }
  
  protected void changeItemPhysics(U newItemPhysics)
  {
    if (_itemPhysics != null)
    {
      _itemPhysics.cleanup();
      newItemPhysics.setup(_itemPhysics);
    }
    _itemPhysics = newItemPhysics;
  }
  
  public String getLabel()
  {
    return _label;
  }
  
  public void setLabel(String label)
  {
    _label = label;
  }
  
  public String getComment()
  {
  	return _comment;
  }
  
  public void setComment(String comment)
  {
  	_comment = comment;
  }
  
  public int getId()
  {
    return _id;
  }
  
  public void setMaxIdleTime(int maxIdleTime)
  {
    _maxIdleTime = maxIdleTime;
  }
  
  public boolean isRetired()
  {
    return _retired;
  }
  
  public void retire()
  {
    _retired = true;
  }
  
  @Override
  public void tick( float frameTime)
  {
    _timeAfterLastBounce += frameTime;
    if (_maxIdleTime > 0 && _timeAfterLastBounce > _maxIdleTime)
    {
      retire();
    }
  }
  
  @Override
  public String toString()
  {
    return _label;
  }
  
  @Override
  public void bounce()
  {
    if (_itemGraphics instanceof IBounceable)
    {
      ((IBounceable) _itemGraphics).bounce();
    }
    if (_itemPhysics instanceof IBounceable)
    {
      ((IBounceable) _itemPhysics).bounce();
    }
    
    for(Module module : _modules)
    {
      if (module == null)
      {
        break;
      }
      if (module instanceof IBounceable)
      {
        ((IBounceable) module).bounce();
      }
    }
    _timeAfterLastBounce = 0;
  }
  
}
