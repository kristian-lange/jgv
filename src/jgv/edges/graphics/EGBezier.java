package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;

public class EGBezier extends EGTemplate
{

  private int _bezierParaX2;
  private int _bezierParaY2;
  private int _bezierParaX3;
  private int _bezierParaY3;

  public EGBezier(boolean labelVisibility, float strokeWeight, Color strokeColor)
  {
    super(labelVisibility, strokeWeight, null, strokeColor, null);
  }
  
  public EGBezier(float strokeWeight)
  {
    this(false, strokeWeight, JGVConstants.EDGE_STROKE_COLOR);
  }
  
  public EGBezier()
  {
    this(false, JGVConstants.EDGE_STROKE_WEIGHT, JGVConstants.EDGE_STROKE_COLOR);
  }

  @Override
  protected void drawEdge()
  {
    _bezierParaX2 = (_xCoordSource + _xCoordTarget) / 2;
    _bezierParaY2 = _yCoordSource;
    _bezierParaX3 = _xCoordTarget;
    _bezierParaY3 = (_yCoordSource + _yCoordTarget) / 2;
    
    _graphics._pApplet.bezier(_xCoordSource, _yCoordSource,
        _bezierParaX2, _bezierParaY2, _bezierParaX3, _bezierParaY3,
        _xCoordTarget, _yCoordTarget);
  }
  
  @Override
  protected void drawLabel()
  {
    int halfBezierX = (int) _graphics._pApplet.bezierPoint(_xCoordSource,
        _bezierParaX2,
        _bezierParaX3, _xCoordTarget, 0.5f);
    int halfBezierY = (int) _graphics._pApplet.bezierPoint(_yCoordSource,
        _bezierParaY2,
        _bezierParaY3, _yCoordTarget, 0.5f);
    drawTextInBox(_item.getComment(), halfBezierX, halfBezierY,
        true, 1.0f);
  }

}
