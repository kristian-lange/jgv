package jgv.item;

import jgv.graphics.Color;
import jgv.graphics.JGVGraphics;
import jgv.input.MouseHandler;
import processing.core.PConstants;

public abstract class JGVItemGraphics
{
  
  protected final static int TEXT_MARGIN_VERTICAL = 3;
  protected final static int TEXT_MARGIN_HORIZONTAL = 3;

  protected IItemForItemGraphics<? extends JGVItemGraphics ,
      ? extends JGVItemPhysics> _item;
  protected JGVGraphics _graphics;
  protected MouseHandler _mouseHandler;
  protected Color _fillColor;
  protected Color _strokeColor;
  protected Color _textColor;
  protected float _strokeWeight = 1.0f;
  
  protected JGVItemGraphics(float strokeWeight,
      Color fillColor, Color strokeColor, Color textColor)
  {
    _strokeWeight = strokeWeight;
    _fillColor = fillColor;
    _strokeColor = strokeColor;
    _textColor = textColor;
  }
  
  public void setup(
      IItemForItemGraphics<? extends JGVItemGraphics , ? extends JGVItemPhysics> item,
      JGVGraphics graphics, MouseHandler mouseHandler)
  {
    _item = item;
    _graphics = graphics;
    _mouseHandler = mouseHandler;
  }
  
  public void setup(JGVItemGraphics oldItemGraphics)
  {
    _item = oldItemGraphics._item;
    _graphics = oldItemGraphics._graphics;
    _mouseHandler = oldItemGraphics._mouseHandler;
  }
  
  protected void setStyle()
  {
    _graphics._pApplet.stroke(_strokeWeight);
    
    if (_strokeColor != null)
    {
      int[] color = _strokeColor.getAsArray();
      _graphics._pApplet.stroke(color[0], color[1], color[2], color[3]);
    }
    else 
    {
      _graphics._pApplet.noStroke();
    }
    
    if (_fillColor != null)
    {
      int[] color = _fillColor.getAsArray();
      _graphics._pApplet.fill(color[0], color[1], color[2], color[3]);
    }
    else 
    {
      _graphics._pApplet.noFill();
    }
  }
  
  public Color getFillColor()
  {
    return _fillColor;
  }
  
  public Color getStrokeColor()
  {
    return _strokeColor;
  }
  
  public Color getTextColor()
  {
    return _textColor;
  }
  
  public abstract float getSizeBoost();
  
  public abstract void setSizeBoost(float sizeBoost);
  
  public abstract float getMinSize();
  
  public abstract void setMinSize(float minSize);
  
  public abstract float getSize();
  
  public abstract void cleanup();
  
  public abstract void drawMouseLeftButton();
  
  public abstract void drawMouseRightButton();
  
  public abstract void drawMouseOver();

  protected void drawTextInBox(String text, int xCoord, int yCoord, 
      boolean border, float strokeWeight)
  {
    _graphics._pApplet.rectMode(PConstants.CENTER);
    
    if (_fillColor != null)
    {
      int[] colorAsArray = _fillColor.getAsArray();
      _graphics._pApplet.fill(colorAsArray[0] + (255 - colorAsArray[0]) * 0.6f,
          colorAsArray[1] + (255 - colorAsArray[1]) * 0.6f,
          colorAsArray[2] + (255 - colorAsArray[2]) * 0.6f,
          220);
    }
    if (_strokeColor != null)
    {
      int[] colorAsArray = _strokeColor.getAsArray();
      _graphics._pApplet.stroke(colorAsArray[0], colorAsArray[1],
          colorAsArray[2], 100);
      _graphics._pApplet.strokeWeight(strokeWeight);
    }
    
    int textHeight = _graphics.getFontSize();
    int textWidth = (int) _graphics._pApplet.textWidth(text);
    _graphics._pApplet.rect(xCoord, yCoord,
        textWidth + TEXT_MARGIN_HORIZONTAL, textHeight + TEXT_MARGIN_VERTICAL);
    
    if (_textColor != null)
    {
      int[] textColor = _textColor.getAsArray();
      _graphics._pApplet.fill(textColor[0], textColor[1], textColor[2], 255);
    }
    _graphics._pApplet.textAlign(PConstants.CENTER, PConstants.CENTER);
    _graphics._pApplet.text(text, xCoord, yCoord);
  }
  
}
