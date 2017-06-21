package jgv.nodes.graphics;

import toxi.geom.Vec2D;
import jgv.JGVConstants;
import jgv.graphics.Color;

public class NGTriangle2 extends NGFigureWithHalo
{
  
  private static final float MYSTERIOUS_FACTOR = 0.53f;

  public NGTriangle2(float strokeWeight, Color fillColor, Color strokeColor,
      Color textColor, float width, int haloWidth)
  {
    super(strokeWeight, fillColor, strokeColor, textColor, width, haloWidth);
  }
  
  public NGTriangle2(float strokeWeight, Color fillColor, Color strokeColor)
  {
    super(strokeWeight, fillColor, strokeColor, JGVConstants.TEXT_COLOR);
  }

  public NGTriangle2(Color fillColor)
  {
    super(fillColor);
  }
  
  public NGTriangle2()
  {
    super();
  }

  @Override
  protected boolean isMouseOver()
  {
    float mouseX = _graphics.getMouseCoordX();
    float mouseY = _graphics.getMouseCoordY();
    Vec2D mouse = new Vec2D(mouseX, mouseY);
    float haloWidth = getSize();
    Vec2D a = new Vec2D(_xCoord - haloWidth, 
        _yCoord - haloWidth * MYSTERIOUS_FACTOR);
    Vec2D b = new Vec2D(_xCoord + haloWidth, 
        _yCoord - haloWidth * MYSTERIOUS_FACTOR);
    Vec2D c = new Vec2D(_xCoord, _yCoord + haloWidth);
    
    if (mouse.isInTriangle(a, b, c))
    {
      return true;
    }
    return false;
  }

	@Override
  protected void drawFigure()
  {
	  _graphics._pApplet.triangle(
						_xCoord - getSize(), _yCoord - getSize() * MYSTERIOUS_FACTOR,
		        _xCoord + getSize(), _yCoord - getSize() * MYSTERIOUS_FACTOR,
		        _xCoord, _yCoord + getSize());	  
  }

	@Override
  protected void drawFigureHalo(float haloWidth)
  {
	  _graphics._pApplet.triangle(
						_xCoord - haloWidth, _yCoord - haloWidth * MYSTERIOUS_FACTOR,
		        _xCoord + haloWidth, _yCoord - haloWidth * MYSTERIOUS_FACTOR,
		        _xCoord, _yCoord + haloWidth);	  
  }


}
