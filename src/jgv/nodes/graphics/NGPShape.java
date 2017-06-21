package jgv.nodes.graphics;

import processing.core.PShape;
import toxi.geom.Vec2D;
import jgv.graphics.Color;

public class NGPShape extends NGFigureWithHalo
{
  
  private int _pShapeIndex;
  
  public NGPShape(int pShapeIndex, Color fillColor, Color textColor,
      float diameter, int haloWidth)
  {
    super(1.0f, fillColor, null, textColor, diameter, haloWidth);
    _pShapeIndex = pShapeIndex;
  }
  
  public NGPShape(int pShapeIndex, Color fillColor)
  {
    super(fillColor);
    _pShapeIndex = pShapeIndex;
  }

  public NGPShape(int pShapeIndex)
  {
    super();
    _pShapeIndex = pShapeIndex;
  }
  
  @Override
  protected boolean isMouseOver()
  {
    float mouseX = _graphics.getMouseCoordX();
    float mouseY = _graphics.getMouseCoordY();
    Vec2D mouse = new Vec2D(mouseX, mouseY);
    Vec2D position = new Vec2D(_xCoord, _yCoord);
    if (mouse.isInCircle(position, getDiameter()))
    {
      return true;
    }
    return false;
  }
  
  private float getDiameter()
  {
    return getSize();
  }

	@Override
  protected void drawFigure()
  {
    PShape pShape = _graphics.getPShape(_pShapeIndex);
    _graphics._pApplet.shape(pShape, _xCoord, _yCoord, getDiameter(),
        getDiameter());	  
  }

	@Override
  protected void drawFigureHalo(float haloWidth)
  {
	  _graphics._pApplet.ellipse(_xCoord, _yCoord, haloWidth, haloWidth);	  
  }

}
