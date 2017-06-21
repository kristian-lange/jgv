package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;
import processing.core.PApplet;

public class EGLineAndArrowFull extends EGLine
{
	
  private final float ARROW_SIZE;
  private final float ARROW_ANGLE;

  public EGLineAndArrowFull(boolean labelVisibility, float strokeWeight,
      Color strokeColor, Color textColor, int arrowAngle, float arrowSize)
  {
    super(labelVisibility, strokeWeight, strokeColor, textColor);
    _fillColor = strokeColor;
    ARROW_ANGLE = PApplet.radians(arrowAngle);
    ARROW_SIZE = arrowSize;
  }
  
  public EGLineAndArrowFull(boolean labelVisibility, float strokeWeight,
  		Color strokeColor, Color textColor)
  {
  	this(labelVisibility, strokeWeight, strokeColor,
  			textColor, JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  public EGLineAndArrowFull(boolean labelVisibility, float strokeWeight, Color strokeColor)
  {
  	this(labelVisibility, strokeWeight, strokeColor,
  			JGVConstants.TEXT_COLOR, JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  public EGLineAndArrowFull(boolean labelVisibility, float strokeWeight)
  {
    this(labelVisibility, strokeWeight,
    		JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR,
    		JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  public EGLineAndArrowFull(boolean labelVisibility)
  {
  	this(labelVisibility,
  	    JGVConstants.EDGE_STROKE_WEIGHT,
  	    JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR,
  	    JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  public EGLineAndArrowFull()
  {
    this(JGVConstants.EDGE_LABEL_VISIBILITY,
        JGVConstants.EDGE_STROKE_WEIGHT,
        JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR,
        JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }

  @Override
  protected void drawEdge()
  {
    float lineAngle = PApplet.atan2(_yCoordSource - _yCoordTarget,
        _xCoordSource - _xCoordTarget);
    float arrow1Angle = lineAngle + ARROW_ANGLE;
    float arrow2Angle = lineAngle - ARROW_ANGLE;

    float xArrow1 = _xCoordTarget + ARROW_SIZE * PApplet.cos(arrow1Angle);
    float yArrow1 = _yCoordTarget + ARROW_SIZE * PApplet.sin(arrow1Angle);
    float xArrow2 = _xCoordTarget + ARROW_SIZE * PApplet.cos(arrow2Angle);
    float yArrow2 = _yCoordTarget + ARROW_SIZE * PApplet.sin(arrow2Angle);
    float xLineTarget = xArrow1 + (xArrow2 - xArrow1) / 2;
    float yLineTarget = yArrow1 + (yArrow2 - yArrow1) / 2;
    
    _graphics._pApplet.line(_xCoordSource, _yCoordSource,
        xLineTarget, yLineTarget);
    _graphics._pApplet.noStroke();
    _graphics._pApplet.triangle(_xCoordTarget, _yCoordTarget, xArrow1, yArrow1,
        xArrow2, yArrow2);
  }

}
