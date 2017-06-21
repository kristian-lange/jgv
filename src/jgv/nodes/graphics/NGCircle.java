package jgv.nodes.graphics;

import toxi.geom.Vec2D;
import jgv.JGVConstants;
import jgv.graphics.Color;

public class NGCircle extends NGFigureWithHalo
{
  
  public NGCircle(float strokeWeight, Color fillColor, Color strokeColor,
      Color textColor, float diameter, int haloWidth)
  {
    super(strokeWeight, fillColor, strokeColor, textColor, diameter, haloWidth);
  }
  
  public NGCircle(float strokeWeight, Color fillColor, Color strokeColor)
  {
    super(strokeWeight, fillColor, strokeColor, JGVConstants.TEXT_COLOR);
  }

  public NGCircle(Color fillColor)
  {
    super(fillColor);
  }
  
  public NGCircle()
  {
    super();
  }
  
  @Override
  protected boolean isMouseOver()
  {
    float mouseX = _graphics.getMouseCoordX();
    float mouseY = _graphics.getMouseCoordY();
    Vec2D mouse = new Vec2D(mouseX, mouseY);
    Vec2D position = new Vec2D(_xCoord, _yCoord);
    if (mouse.isInCircle(position, getDiameter() * 0.5f))
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
		_graphics._pApplet.ellipse(_xCoord, _yCoord, getDiameter(), getDiameter());	  
  }

	@Override
  protected void drawFigureHalo(float haloWidht)
  {
	  _graphics._pApplet.ellipse(_xCoord, _yCoord, haloWidht, haloWidht);	  
  }

}
