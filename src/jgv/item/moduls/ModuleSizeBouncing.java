package jgv.item.moduls;

import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;
import jgv.item.JGVItemGraphics;

public class ModuleSizeBouncing extends Module implements IBounceable, ITickable
{
  
  private static final float SIZE_BOOST_FACTOR_DEF = 0.5f;
  private static final float SHRINK_FACTOR_DEF = 0.85f;
  
  private final float SIZE_BOOST_FACTOR;
  private final float SHRINK_FACTOR;
	private final boolean BOUNCE_AT_CREATION;

  public ModuleSizeBouncing(float sizeBoostFactor, float shrinkFactor,
  		boolean bounceAtCreation)
  {
    SIZE_BOOST_FACTOR = sizeBoostFactor;
    SHRINK_FACTOR = shrinkFactor;
    BOUNCE_AT_CREATION = bounceAtCreation;
  }
  
  public ModuleSizeBouncing()
  {
    this(SIZE_BOOST_FACTOR_DEF, SHRINK_FACTOR_DEF, true);
  }

  private void resetAnimation()
  {
  	JGVItemGraphics jGVItemGraphics = getItem().getItemGraphics();
    jGVItemGraphics.setSizeBoost(jGVItemGraphics.getMinSize() * SIZE_BOOST_FACTOR);
  }

  private void stepAnimation()
  {
  	JGVItemGraphics jGVItemGraphics = getItem().getItemGraphics(); 
    jGVItemGraphics.setSizeBoost(jGVItemGraphics.getSizeBoost() * SHRINK_FACTOR);
  }

  @Override
  public void bounce()
  {
    resetAnimation();
  }

  @Override
  public void tick(float frameTime)
  {
    stepAnimation();
  }
  
  @Override
  public void cleanup()
  {
    // nothing to do
  }

	@Override
  public void setup()
  {
		if (BOUNCE_AT_CREATION)
		{
			resetAnimation();
		}
  }

}
