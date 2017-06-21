package jgv.nodes.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;
import processing.core.PConstants;
import toxi.geom.Rect;
import toxi.geom.Vec2D;

public class NGLabelBox extends NGTemplate
{
  private final static int TEXT_MARGIN_VERTICAL_DEF = 3;
  private final static int TEXT_MARGIN_HORIZONTAL_DEF = 3;
  
  private final int TEXT_MARGIN_VERTICAL;
  private final int TEXT_MARGIN_HORIZONTAL;
  private int _labelHeight;
  private int _labelWidth;
  
  public NGLabelBox(int textMarginVertical, int textMarginHorizontal,
      float strokeWeight, Color fillColor, Color strokeColor, Color textColor)
  {
    super(strokeWeight, fillColor, strokeColor, textColor, 1.0f);
    TEXT_MARGIN_VERTICAL = textMarginVertical;
    TEXT_MARGIN_HORIZONTAL = textMarginHorizontal;
  }

  public NGLabelBox(float strokeWeight, Color fillColor, Color strokeColor, Color textColor)
  {
    this(TEXT_MARGIN_VERTICAL_DEF, TEXT_MARGIN_HORIZONTAL_DEF,
        strokeWeight, fillColor, strokeColor, textColor);
  }
  
  public NGLabelBox(Color color)
  {
    this(TEXT_MARGIN_VERTICAL_DEF, TEXT_MARGIN_HORIZONTAL_DEF,
        JGVConstants.NODE_STROKE_WEIGHT, color, JGVConstants.NODE_STROKE_COLOR,
        JGVConstants.TEXT_COLOR);
  }
  
  public NGLabelBox()
  {
    this(TEXT_MARGIN_VERTICAL_DEF, TEXT_MARGIN_HORIZONTAL_DEF,
        JGVConstants.NODE_STROKE_WEIGHT, JGVConstants.NODE_FILL_COLOR, 
        JGVConstants.NODE_STROKE_COLOR, JGVConstants.TEXT_COLOR);
  }
  
  @Override
  protected void drawLabel()
  {
    // nothing to do
  }

  @Override
  protected void drawNode()
  {
    _labelHeight = _graphics.getFontSize();
    _labelWidth = (int) _graphics._pApplet.textWidth(_node.getLabel());
    _graphics._pApplet.rect(_xCoord, _yCoord,
        _labelWidth + TEXT_MARGIN_HORIZONTAL,
        _labelHeight + TEXT_MARGIN_VERTICAL);
    
    int[] textColor = _textColor.getAsArray();
    _graphics._pApplet.fill(textColor[0], textColor[1], textColor[2]);
    _graphics._pApplet.textAlign(PConstants.CENTER, PConstants.CENTER);
    _graphics._pApplet.text(_node.getLabel(), _xCoord, _yCoord);
  }

  @Override
  protected boolean isMouseOver()
  {
    float mouseX = _graphics.getMouseCoordX();
    float mouseY = _graphics.getMouseCoordY();
    Vec2D mouse = new Vec2D(mouseX, mouseY);
    Rect box = new Rect(_xCoord, _yCoord,
        _labelWidth + TEXT_MARGIN_HORIZONTAL, _labelHeight + TEXT_MARGIN_VERTICAL);
    if (mouse.isInRectangle(box))
    {
      return true;
    }
    return false;
  }

  @Override
  public float getSizeBoost()
  {
    return _labelWidth;
  }

  @Override
  public void setSizeBoost(float size)
  {
    // nothing to do
  }

}
