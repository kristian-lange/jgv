package jgv.graphics;

import processing.core.PShape;

public interface IGraphicsForItems
{
  
  public PShape getPShape(int pShapeIndex) throws IndexOutOfBoundsException;
  
  public int getFontSize();
  
  public float getMouseCoordX();
  
  public float getMouseCoordY();
  
  public boolean drawCommentIfMouseOver();
  
}
