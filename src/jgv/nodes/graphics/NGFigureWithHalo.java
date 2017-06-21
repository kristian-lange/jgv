package jgv.nodes.graphics;

import processing.core.PConstants;
import jgv.JGVConstants;
import jgv.graphics.Color;

public abstract class NGFigureWithHalo extends NGTemplate
{
  
  private static final float HALO_WIDTH_DEF = 5f;
  private static final float HALO_ALPHA_FACTOR_DEF = 0.3f;
  private final float HALO_ALPHA_FACTOR;
  protected final float HALO_WIDTH;

  public NGFigureWithHalo(float strokeWeight, Color fillColor, Color strokeColor, Color textColor,
      float width, float haloWidth)
  {
    super(strokeWeight, fillColor, strokeColor, textColor, width);
    HALO_WIDTH = haloWidth;
    HALO_ALPHA_FACTOR = HALO_ALPHA_FACTOR_DEF;
  }
  
  public NGFigureWithHalo(float strokeWeight, Color fillColor, Color strokeColor, Color textColor)
  {
    this(strokeWeight, fillColor, strokeColor, textColor,
        JGVConstants.NODE_WIDTH, HALO_WIDTH_DEF);
  }

  public NGFigureWithHalo(Color fillColor)
  {
    this(JGVConstants.NODE_STROKE_WEIGHT, fillColor,
    		JGVConstants.NODE_STROKE_COLOR, JGVConstants.TEXT_COLOR, 
    		JGVConstants.NODE_WIDTH, HALO_WIDTH_DEF);
  }
  
  public NGFigureWithHalo()
  {
    this(JGVConstants.NODE_STROKE_WEIGHT,
    		JGVConstants.NODE_FILL_COLOR, JGVConstants.TEXT_COLOR,
    		JGVConstants.NODE_STROKE_COLOR, JGVConstants.NODE_WIDTH, HALO_WIDTH_DEF);
  }
  
  @Override
  protected void drawNode()
  {
    drawFigure();
   
    if (_fillColor != null)
    {
    	int[] fillColor = _fillColor.getAsArray();
    	_graphics._pApplet.fill(fillColor[0], fillColor[1], fillColor[2],
    	    fillColor[3] * HALO_ALPHA_FACTOR);
    }
    if (_strokeColor != null)
    {
    	int[] strokeColor = _strokeColor.getAsArray();
    	_graphics._pApplet.stroke(strokeColor[0], strokeColor[1], strokeColor[2],
    					strokeColor[3] * HALO_ALPHA_FACTOR);
    }
    float haloWidth = getSize() + HALO_WIDTH;
    drawFigureHalo(haloWidth);
  }
  
  protected abstract void drawFigure();

  protected abstract void drawFigureHalo(float haloWidth);
  
  @Override
  protected void drawLabel()
  {
  	int[] textColor = _textColor.getAsArray();
  	_graphics._pApplet.fill(textColor[0], textColor[1], textColor[2],
  	    textColor[3]);
  	_graphics._pApplet.textAlign(PConstants.CENTER, PConstants.CENTER);
  	_graphics._pApplet.text(_node.getLabel(), _xCoord, _yCoord);
  }

}
