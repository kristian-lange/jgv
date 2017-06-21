package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;

public class EGJelly extends EGTemplate
{

  public EGJelly(float strokeWeight, Color strokeColor)
  {
    super(false, strokeWeight, null, strokeColor, null);
  }
  
  public EGJelly(float strokeWeight)
  {
    this(strokeWeight, JGVConstants.EDGE_STROKE_COLOR);
  }
  
  public EGJelly()
  {
    this(JGVConstants.EDGE_STROKE_WEIGHT, JGVConstants.EDGE_STROKE_COLOR);
  }

  @Override
  protected void drawEdge()
  {
    float xVelocitySource = _edge.getEdgePhysics().getSourceVelocityX();
    float yVelocitySource = _edge.getEdgePhysics().getSourceVelocityY();
    float xVelocityTarget = _edge.getEdgePhysics().getTargetVelocityX();
    float yVelocityTarget = _edge.getEdgePhysics().getTargetVelocityY();
    int x2 = (int) (_xCoordSource - xVelocitySource 
        * _graphics._pApplet.width * 2);
    int y2 = (int) (_yCoordSource - yVelocitySource 
        * _graphics._pApplet.height * 2);
    int x3 = (int) (_xCoordTarget - xVelocityTarget 
        * _graphics._pApplet.width * 2);
    int y3 = (int) (_yCoordTarget - yVelocityTarget 
        * _graphics._pApplet.height * 2);
    
    _graphics._pApplet.bezier(_xCoordSource, _yCoordSource, x2, y2, x3, y3, 
        _xCoordTarget, _yCoordTarget);
  }

  @Override
  protected void drawLabel()
  {
    // nothing to do
  }

}
