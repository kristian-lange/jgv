package jgv.item.moduls;

import jgv.graphics.Color;
import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;
import jgv.item.JGVItemGraphics;
import jgv.item.JGVItemPhysics;
import jgv.item.JGVItem;

public class ModuleColorTextBouncing extends Module implements IBounceable, ITickable
{
  
  private final static int TEXT_ALPHA_BOUNCED_DEF = 200;
  private final static int TEXT_ALPHA_UNBOUNCED_DEF = 100;
  private final static float TEXT_ALPHA_FACTOR_DEF = 0.93f;
  private final int TEXT_ALPHA_BOUNCED;
  private final int TEXT_ALPHA_UNBOUNCED;
  private final float TEXT_ALPHA_FACTOR;
  private final boolean BOUNCE_AT_CREATION;
  
	private boolean _wasBounced = false;

  public ModuleColorTextBouncing(int textAlphaBounced, int textAlphaUnbounced,
  		float textChangeFactor,  boolean bounceAtCreation)
  {
    TEXT_ALPHA_BOUNCED = textAlphaBounced;
    TEXT_ALPHA_UNBOUNCED = textAlphaUnbounced;
    TEXT_ALPHA_FACTOR = textChangeFactor;
    BOUNCE_AT_CREATION = bounceAtCreation;
  }
  
  public ModuleColorTextBouncing(JGVItem<? extends JGVItemGraphics, ? extends JGVItemPhysics> item)
  {
    this(TEXT_ALPHA_BOUNCED_DEF, TEXT_ALPHA_UNBOUNCED_DEF, TEXT_ALPHA_FACTOR_DEF, true);
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
    Color textColor = getItem().getItemGraphics().getTextColor();
    if (textColor != null)
    {
      textColor.setAlpha(TEXT_ALPHA_BOUNCED);
    }
  }

  @Override
  public void tick(float frameTime)
  {
  	if (!_wasBounced)
  	{
  		return;
  	}
  	Color textColor = getItem().getItemGraphics().getTextColor();
    if (textColor != null && textColor.getAlpha() > TEXT_ALPHA_UNBOUNCED)
    {
      textColor.setAlpha((int) (textColor.getAlpha() * TEXT_ALPHA_FACTOR));
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
