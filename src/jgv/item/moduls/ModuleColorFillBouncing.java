package jgv.item.moduls;

import jgv.graphics.Color;
import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;

public class ModuleColorFillBouncing extends Module implements IBounceable, ITickable
{
  
  private final static int FILL_ALPHA_BOUNCED_DEF = 200;
  private final static int FILL_ALPHA_UNBOUNCED_DEF = 100;
  private final static float FILL_ALPHA_FACTOR_DEF = 0.93f;
  private final int FILL_ALPHA_BOUNCED;
  private final int FILL_ALPHA_UNBOUNCED;
  private final float FILL_ALPHA_FACTOR;
  private final boolean BOUNCE_AT_CREATION;
  
	private boolean _wasBounced = false;

  public ModuleColorFillBouncing(int fillAlphaBounced, int fillAlphaUnbounced,
  		float fillChangeFactor,  boolean bounceAtCreation)
  {
    FILL_ALPHA_BOUNCED = fillAlphaBounced;
    FILL_ALPHA_UNBOUNCED = fillAlphaUnbounced;
    FILL_ALPHA_FACTOR = fillChangeFactor;
    BOUNCE_AT_CREATION = bounceAtCreation;
  }
  
  public ModuleColorFillBouncing()
  {
    this(FILL_ALPHA_BOUNCED_DEF, FILL_ALPHA_UNBOUNCED_DEF, FILL_ALPHA_FACTOR_DEF, true);
  }
  
  public void setup()
  {
  	if (BOUNCE_AT_CREATION)
  	{
  		bounce();
  	}
  }
  
  @Override
  public void bounce()
  {
  	_wasBounced  = true;
    Color fillColor = getItem().getItemGraphics().getFillColor();
    if (fillColor != null)
    {
      fillColor.setAlpha(FILL_ALPHA_BOUNCED);
    }
  }

  @Override
  public void tick(float frameTime)
  {
  	if (!_wasBounced)
  	{
  		return;
  	}
  	Color fillColor = getItem().getItemGraphics().getFillColor();
    if (fillColor != null && fillColor.getAlpha() > FILL_ALPHA_UNBOUNCED)
    {
      fillColor.setAlpha((int) (fillColor.getAlpha() * FILL_ALPHA_FACTOR));
    }
    else
    {
    	_wasBounced = false;
    }
  }

  @Override
  public void cleanup()
  {
    // nothing to do
  }

}
