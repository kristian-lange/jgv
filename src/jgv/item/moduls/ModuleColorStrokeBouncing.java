package jgv.item.moduls;

import jgv.graphics.Color;
import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;

public class ModuleColorStrokeBouncing extends Module implements IBounceable, ITickable
{
  
  private final static int STROKE_ALPHA_BOUNCED_DEF = 200;
  private final static int STROKE_ALPHA_UNBOUNCED_DEF = 100;
  private final static float STROKE_ALPHA_FACTOR_DEF = 0.93f;
  private final int STROKE_ALPHA_BOUNCED;
  private final int STROKE_ALPHA_UNBOUNCED;
  private final float STROKE_ALPHA_FACTOR;
  private final boolean BOUNCE_AT_CREATION;
  
	private boolean _wasBounced = false;

  public ModuleColorStrokeBouncing(int strokeAlphaBounced, int strokeAlphaUnbounced,
  		float strokeChangeFactor, boolean bounceAtCreation)
  {
    STROKE_ALPHA_BOUNCED = strokeAlphaBounced;
    STROKE_ALPHA_UNBOUNCED = strokeAlphaUnbounced;
    STROKE_ALPHA_FACTOR = strokeChangeFactor;
    BOUNCE_AT_CREATION = bounceAtCreation;
  }
  
  public ModuleColorStrokeBouncing()
  {
    this(STROKE_ALPHA_BOUNCED_DEF, STROKE_ALPHA_UNBOUNCED_DEF, STROKE_ALPHA_FACTOR_DEF, true);
  }
  
  @Override
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
    Color strokeColor = getItem().getItemGraphics().getStrokeColor();
    if (strokeColor != null)
    {
      strokeColor.setAlpha(STROKE_ALPHA_BOUNCED);
    }
  }

  @Override
  public void tick(float frameTime)
  {
  	if (!_wasBounced)
  	{
  		return;
  	}
  	Color strokeColor = getItem().getItemGraphics().getStrokeColor();
    if (strokeColor != null && strokeColor.getAlpha() > STROKE_ALPHA_UNBOUNCED)
    {
      strokeColor.setAlpha((int) (strokeColor.getAlpha() * STROKE_ALPHA_FACTOR));
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
