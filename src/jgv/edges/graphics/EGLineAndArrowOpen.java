package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;
import processing.core.PApplet;

public class EGLineAndArrowOpen extends EGLine
{
  private final float ARROW_SIZE;
  private final float ARROW_ANGLE;

  public EGLineAndArrowOpen(boolean labelVisibility, float strokeWeight,
      Color strokeColor, Color textColor, int arrowAngle, float arrowSize)
  {
    super(labelVisibility, strokeWeight, strokeColor, textColor);
    ARROW_ANGLE = PApplet.radians(arrowAngle);
    ARROW_SIZE = arrowSize;
  }
  
  public EGLineAndArrowOpen(boolean labelVisibility, float strokeWeight,
  	  Color strokeColor, Color textColor)
  {
  	this(labelVisibility, strokeWeight, strokeColor,
  			textColor, JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
    
  public EGLineAndArrowOpen(boolean labelVisibility, float strokeWeight,
  	  Color strokeColor)
  {
  	this(labelVisibility, strokeWeight, strokeColor, JGVConstants.TEXT_COLOR,
  			JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }

  public EGLineAndArrowOpen(boolean labelVisibility, float strokeWeight)
  {
    this(labelVisibility, strokeWeight,
    		JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR,
    		JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  public EGLineAndArrowOpen(boolean labelVisibility)
  {
  	this(labelVisibility,
  	    JGVConstants.EDGE_STROKE_WEIGHT,
  	    JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR,
  	    JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  public EGLineAndArrowOpen()
  {
    this(JGVConstants.EDGE_LABEL_VISIBILITY,
        JGVConstants.EDGE_STROKE_WEIGHT,
        JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR,
        JGVConstants.EDGE_ARROW_ANGLE, JGVConstants.EDGE_ARROW_SIZE);
  }
  
  @Override
  protected void drawEdge()
  {
    super.drawEdge();
    float lineAngle = PApplet.atan2(_yCoordSource - _yCoordTarget, 
        _xCoordSource - _xCoordTarget);
    float arrowPart1Angle = lineAngle + ARROW_ANGLE;
    float arrowPart2Angle = lineAngle - ARROW_ANGLE;

    float xArrowPart1 = _xCoordTarget 
        + ARROW_SIZE * PApplet.cos(arrowPart1Angle);
    float yArrowPart1 = _yCoordTarget 
        + ARROW_SIZE * PApplet.sin(arrowPart1Angle);
    float xArrowPart2 = _xCoordTarget 
        + ARROW_SIZE * PApplet.cos(arrowPart2Angle);
    float yArrowPart2 = _yCoordTarget 
        + ARROW_SIZE * PApplet.sin(arrowPart2Angle);
  	
    _graphics._pApplet.line(_xCoordTarget, _yCoordTarget, 
        xArrowPart1, yArrowPart1);
    _graphics._pApplet.line(_xCoordTarget, _yCoordTarget,
        xArrowPart2, yArrowPart2);
  }

}
