package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;
import processing.core.PConstants;

public class EGLine extends EGTemplate
{
	
  public EGLine(boolean labelVisibility, float strokeWeight,
  		Color strokeColor, Color textColor)
  {
    super(labelVisibility, strokeWeight, null, strokeColor, textColor);
  }
  
  public EGLine(boolean labelVisibility)
  {
    this(labelVisibility, JGVConstants.EDGE_STROKE_WEIGHT,
    		JGVConstants.EDGE_STROKE_COLOR,	JGVConstants.TEXT_COLOR);
  }
  
  public EGLine()
  {
    this(JGVConstants.EDGE_LABEL_VISIBILITY,
        JGVConstants.EDGE_STROKE_WEIGHT, JGVConstants.EDGE_STROKE_COLOR,
        JGVConstants.TEXT_COLOR);
  }

  @Override
  protected void drawEdge()
  {
    _graphics._pApplet.line(
        _xCoordSource, _yCoordSource, _xCoordTarget, _yCoordTarget);
  }

  @Override
  protected void drawLabel()
  {
    int textAlignX = PConstants.CENTER;
    int textAlignY = PConstants.CENTER;
    
    int xCoord = (_xCoordTarget - _xCoordSource) / 2 + _xCoordSource;
    int yCoord = (_yCoordTarget - _yCoordSource) / 2 + _yCoordSource;
    
    _graphics._pApplet.textAlign(textAlignX, textAlignY);
    int[] color = _textColor.getAsArray();
    _graphics._pApplet.fill(color[0], color[1], color[2], color[3]);
    _graphics._pApplet.text(_item.getLabel(), xCoord, yCoord);
  }

  @Override
  public void cleanup()
  {
    // nothing to do    
  }

}
