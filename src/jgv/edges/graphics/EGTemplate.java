package jgv.edges.graphics;

import jgv.graphics.Color;
import jgv.interfaces.ITickable;

public abstract class EGTemplate extends JGVEdgeGraphics implements ITickable
{
	
  protected int _xCoordSource;
  protected int _yCoordSource;
  protected int _xCoordTarget;
  protected int _yCoordTarget;
	
  public EGTemplate(boolean labelVisibility,
      float strokeWeight, Color fillColor, Color strokeColor, Color textColor)
  {
    super(labelVisibility, strokeWeight, fillColor, strokeColor, textColor);
  }
  
  @Override
  public void tick(float frameTime)
  {
    draw();
  }

  public void draw()
  {
    _xCoordSource = (int) (_edge.getEdgePhysics().getSourceX()
        * _graphics._pApplet.width);
    _yCoordSource = (int) (_edge.getEdgePhysics().getSourceY()
        * _graphics._pApplet.height);
    _xCoordTarget = (int) (_edge.getEdgePhysics().getTargetX()
        * _graphics._pApplet.width);
    _yCoordTarget = (int) (_edge.getEdgePhysics().getTargetY()
        * _graphics._pApplet.height);
    
    _graphics._pApplet.pushStyle();
    setStyle();
    
    drawEdge();
    
    if (isLabelVisible())
    {
      drawLabel();
    }
    
    _graphics._pApplet.popStyle();
  }
  
  protected abstract void drawEdge();
  
  protected abstract void drawLabel();
  
  @Override
  public void cleanup()
  {
  	// nothing to do
  }
  
  @Override
  public void drawMouseLeftButton()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void drawMouseOver()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void drawMouseRightButton()
  {
    // TODO Auto-generated method stub
    
  }
  
}
