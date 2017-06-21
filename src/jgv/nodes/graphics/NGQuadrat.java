package jgv.nodes.graphics;

import toxi.geom.Rect;
import toxi.geom.Vec2D;
import jgv.JGVConstants;
import jgv.graphics.Color;

public class NGQuadrat extends NGFigureWithHalo
{
  
  public NGQuadrat(float strokeWeight, Color fillColor, Color strokeColor, Color textColor,
      float width, int haloWidth)
  {
    super(strokeWeight, fillColor, strokeColor, textColor, width, haloWidth);
  }
  
  public NGQuadrat(float strokeWeight, Color fillColor, Color strokeColor)
  {
    super(strokeWeight, fillColor, strokeColor, JGVConstants.TEXT_COLOR);
  }
  
  public NGQuadrat(Color fillColor)
  {
    super(fillColor);
  }
  
  public NGQuadrat()
  {
    super();
  }
  
  @Override
  protected boolean isMouseOver()
  {
    float mouseX = _graphics.getMouseCoordX();
    float mouseY = _graphics.getMouseCoordY();
    Vec2D mouse = new Vec2D(mouseX, mouseY);
    float size = getSize();
    Rect rect = new Rect(_xCoord - size * 0.5f, _yCoord - size * 0.5f, size, size);
    
    if (mouse.isInRectangle(rect))
    {
      return true;
    }
    return false;
  }

	@Override
  protected void drawFigure()
  {
		_graphics._pApplet.rect(_xCoord, _yCoord, getSize(), getSize());	  
  }

	@Override
  protected void drawFigureHalo(float haloWidht)
  {
	  _graphics._pApplet.rect(_xCoord, _yCoord, haloWidht, haloWidht);	  
  }

}
