package jgv.item.moduls;

import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;

public class ModuleSizeBouncingRate extends Module implements IBounceable, 
    ITickable
{
  
  private static final int LOG_FACTOR_DEF = 6;
  private final int LOG_FACTOR;
  private float _sizeMin;
  private float _sizeMinTarget;
  private float _cronJobCounter1 = 0;
  private float _cronJobCounter10 = 0;
  private boolean _cronJob1 = false;
  private boolean _cronJob10 = false;
  
  private int _bounceCounter = 0;

  public ModuleSizeBouncingRate(int logFactor)
  {
    LOG_FACTOR = logFactor;
  }
  
  public ModuleSizeBouncingRate()
  {
    this(LOG_FACTOR_DEF);
  }
  
  @Override
  public void setup()
  {
  	bounce();
  	_sizeMin = getItem().getItemGraphics().getMinSize();
  	_sizeMinTarget = _sizeMin;
  }
  
  @Override
  public void bounce()
  {
    _bounceCounter++;
  }

  public void cronJob1()
  {
    float sizeMinCurrent = getItem().getItemGraphics().getMinSize();
    if (_sizeMinTarget > sizeMinCurrent)
    {
      getItem().getItemGraphics().setMinSize(sizeMinCurrent + 1.0f); 
    }
    if (_sizeMinTarget < sizeMinCurrent)
    {
      getItem().getItemGraphics().setMinSize(sizeMinCurrent - 1.0f); 
    }
  }
  
  public void cronJob10()
  {
    // The goal is to have a width that increases with the bouncing rate, but
    // increases lesser and lesser with increasing bouncing rate and finally 
    // doesn't increase any more at all.
    // Increase the LOG_FACTOR to get a higher end width.
    // This strange calculation is due to performance.
    _sizeMinTarget = _sizeMin + LOG_FACTOR * binlog(_bounceCounter + 1);
    _bounceCounter = 0;
  }
  
  private int binlog( int bits ) // returns 0 for bits=0
  {
      int log = 0;
      if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
      if( bits >= 256 ) { bits >>>= 8; log += 8; }
      if( bits >= 16  ) { bits >>>= 4; log += 4; }
      if( bits >= 4   ) { bits >>>= 2; log += 2; }
      return log + ( bits >>> 1 );
  }

  @Override
  public void cleanup()
  {
    // nothing to do
  }

  @Override
  public void tick(float frameTime)
  {
    count(frameTime);
    if (_cronJob1)
    {
      cronJob1();
    }
    if (_cronJob10)
    {
      cronJob10();
    }
  }
  
  private void count(float frameTime)
  {
    _cronJobCounter1 += frameTime;
    _cronJobCounter10 += frameTime;
    _cronJob1 = false;
    _cronJob10 = false;
    if (_cronJobCounter1 >= 1)
    {
      _cronJob1  = true;
      _cronJobCounter1 = 0;
    }
    if (_cronJobCounter10 >= 10)
    {
      _cronJob10  = true;
      _cronJobCounter10 = 0;
    }
  }
  
}
