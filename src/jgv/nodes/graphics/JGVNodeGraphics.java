package jgv.nodes.graphics;

import jgv.graphics.Color;
import jgv.graphics.JGVGraphics;
import jgv.input.MouseHandler;
import jgv.item.JGVItemGraphics;
import jgv.nodes.INodeForNodeGraphics;

public abstract class JGVNodeGraphics extends JGVItemGraphics
{
  
  protected INodeForNodeGraphics _node;
  private float _sizeBoost = 0;
  private float _minSize;
  
  protected JGVNodeGraphics(float strokeWeight,
      Color fillColor, Color strokeColor, Color textColor, float minSize)
  {
    super(strokeWeight, fillColor, strokeColor, textColor);
    _minSize = minSize;
  }
  
  public void setup(INodeForNodeGraphics node, JGVGraphics graphics,
      MouseHandler mouseHandler)
  {
    super.setup(node, graphics, mouseHandler);
    _node = node;
  }
  
  public void setup(JGVNodeGraphics oldNodeGraphics)
  {
    super.setup(oldNodeGraphics);
    _node = oldNodeGraphics._node;
  }

  @Override
  public float getSizeBoost()
  {
    return _sizeBoost;
  }
  
  @Override
  public void setSizeBoost(float sizeBoost)
  {
    _sizeBoost = sizeBoost;
  }
  
  @Override
  public float getMinSize()
  {
    return _minSize;
  }

  @Override
  public void setMinSize(float minSize)
  {
    _minSize = minSize;
  }
  
  @Override
  public float getSize()
  {
  	return _minSize + _sizeBoost;
  }
  
}
