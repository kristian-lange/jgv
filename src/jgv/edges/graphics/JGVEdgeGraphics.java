package jgv.edges.graphics;

import jgv.edges.IEdgeForEdgeGraphics;
import jgv.graphics.Color;
import jgv.graphics.JGVGraphics;
import jgv.input.MouseHandler;
import jgv.item.JGVItemGraphics;

public abstract class JGVEdgeGraphics extends JGVItemGraphics
{
	
  protected IEdgeForEdgeGraphics _edge;
	private float _strokeWeightMin;
	private float _strokeWeightBoost = 0; 
  private boolean _labelVisibility;
  
  protected JGVEdgeGraphics(boolean labelVisibility,
      float strokeWeight, Color fillColor, Color strokeColor, Color textColor)
  {
    super(strokeWeight, fillColor, strokeColor, textColor);
    _strokeWeightMin = strokeWeight;
    _labelVisibility = labelVisibility;
  }
  
  public void setup(IEdgeForEdgeGraphics edge, JGVGraphics graphics,
      MouseHandler mouseHandler)
  {
    super.setup(edge, graphics, mouseHandler);
    _edge = edge;
  }
  
  public void setup(JGVEdgeGraphics oldEdgeGraphics)
  {
    super.setup(oldEdgeGraphics);
    _edge = oldEdgeGraphics._edge;
  }
  
  public boolean isLabelVisible()
  {
    return _labelVisibility;
  }
  
  @Override
  public float getMinSize()
  {
    return _strokeWeightMin;
  }

  @Override
  public void setMinSize(float minSize)
  {
  	_strokeWeightMin = minSize;
  	_graphics._pApplet.strokeWeight(_strokeWeightMin + _strokeWeightBoost);   
  }
  
  @Override
  public float getSizeBoost()
  {
    return _strokeWeightBoost ;
  }

  @Override
  public void setSizeBoost(float sizeBoost)
  {
    _strokeWeightBoost = sizeBoost;
    _strokeWeight = _strokeWeightMin + _strokeWeightBoost;
  }
  
	@Override
  public float getSize()
  {
	  return _strokeWeightMin + _strokeWeightBoost;
  }
  
}
