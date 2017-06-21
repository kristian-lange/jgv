package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;
import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;

public class EGBroadcast extends JGVEdgeGraphics implements ITickable, IBounceable
{

  private static final float DIAMETER_START = 50.0f;
  private static final float DIAMETER_END = 400.0f;
  private static final float DIAMETER_SPEED = 1.12f;
  private float _diameter = DIAMETER_START;
  private boolean _visible = true;
  
  public EGBroadcast(float strokeWeight, Color strokeColor)
  {
    super(false, strokeWeight, null, strokeColor, null);
  }
  
  public EGBroadcast(Color strokeColor)
  {
    this(JGVConstants.EDGE_STROKE_WEIGHT, strokeColor);
  }
  
  public EGBroadcast()
  {
    this(JGVConstants.EDGE_STROKE_WEIGHT, JGVConstants.EDGE_STROKE_COLOR);
  }
  
  @Override
  public void tick(float frameTime)
  {
    draw(_edge.getEdgePhysics().getSourceX(),
        _edge.getEdgePhysics().getSourceY());
    stepAnimation();
  }
  
  public void draw(float sourceX, float sourceY)
  {
    if (!_visible)
    {
      return;
    }
    
    int xSource = (int) (sourceX * _graphics._pApplet.width);
    int ySource = (int) (sourceY * _graphics._pApplet.height);

    _graphics._pApplet.pushStyle();
    setStyle();
    _graphics._pApplet.ellipse(xSource, ySource, _diameter, _diameter);
    _graphics._pApplet.popStyle();
    
    stepAnimation();
  }
  
  private void resetAnimation()
  {
    _diameter = DIAMETER_START;
    _visible = true;    
  }
  
  private void stepAnimation()
  {
    _diameter *= DIAMETER_SPEED;
    if (_diameter > DIAMETER_END)
    {
      _visible = false;
    }    
  }

  @Override
  public void bounce()
  {
    resetAnimation();
  }
  
  @Override
  public void drawMouseLeftButton()
  {
    // nothing to do
  }

  @Override
  public void drawMouseOver()
  {
    // nothing to do
  }

  @Override
  public void drawMouseRightButton()
  {
    // nothing to do
  }

	@Override
  public void cleanup()
  {
		// nothing to do
  }
  
}
