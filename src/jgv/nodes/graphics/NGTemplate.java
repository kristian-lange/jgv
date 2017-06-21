package jgv.nodes.graphics;

import jgv.graphics.Color;
import jgv.interfaces.ITickable;
import jgv.nodes.physics.JGVNodePhysics;

public abstract class NGTemplate extends JGVNodeGraphics implements ITickable
{
	
  protected final static int TEXT_MARGIN_VERTICAL = 3;
  protected final static int TEXT_MARGIN_HORIZONTAL = 3;
  
  protected int _xCoord;
  protected int _yCoord;
  
  protected NGTemplate(float strokeWeight,
      Color fillColor, Color strokeColor, Color textColor, float size)
  {
    super(strokeWeight, fillColor, strokeColor, textColor, size);
  }
  
  @Override
  public void tick(float frameTime)
  {
    draw();
  }

  protected abstract boolean isMouseOver();

  public void draw()
  {
    JGVNodePhysics jGVNodePhysics = _node.getNodePhysics();
    _xCoord = (int) (jGVNodePhysics.getX() * _graphics._pApplet.width);
    _yCoord = (int) (jGVNodePhysics.getY() * _graphics._pApplet.height);
    
    if (_graphics.drawCommentIfMouseOver() && isMouseOver())
    {
      _mouseHandler.addToDrawCommentLater(this);
    }
    
    _graphics._pApplet.pushStyle();
    setStyle();
    drawNode();
    _graphics._pApplet.popStyle();
  }
  
  @Override
  public void drawMouseLeftButton()
  {
    drawTextInBox(_node.getLabel(), _xCoord, _yCoord,
        true, _strokeWeight);
  }

  @Override
  public void drawMouseOver()
  {
    if (_node.getComment() == null)
    {
      return;
    }
    drawTextInBox(_node.getComment(), _xCoord, _yCoord,
        true, _strokeWeight);
  }

  @Override
  public void drawMouseRightButton()
  {
    // nothing to do
  }
  
  @Override
  public void cleanup()
  {
  	// nothing to do
  }
  
  protected abstract void drawNode();
  
  protected abstract void drawLabel();

}
