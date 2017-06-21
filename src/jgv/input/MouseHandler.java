package jgv.input;

import java.util.Calendar;

import processing.core.PConstants;

import jgv.graphics.JGVGraphics;
import jgv.interfaces.ITickable;
import jgv.item.JGVItemGraphics;

public class MouseHandler implements ITickable
{
  
  public static final int MAX_GRAPHICSITEMS = 10;
  public static final int WAIT_BETW_CLICKS = 400;
  
  private JGVGraphics _graphics;
  private JGVItemGraphics[] _itemGraphicsToDrawComments = 
      new JGVItemGraphics[MAX_GRAPHICSITEMS];
  private long _timeOfLastMouseLeft = Calendar.getInstance().getTimeInMillis();
  private int _nextItemIdx = 0;
  
  public MouseHandler(JGVGraphics graphics)
  {
    _graphics = graphics;
  }

  public void addToDrawCommentLater(JGVItemGraphics jGVItemGraphics)
  {
    if (_nextItemIdx < MAX_GRAPHICSITEMS)
    {
      _itemGraphicsToDrawComments[_nextItemIdx] = jGVItemGraphics;
      _nextItemIdx++;
    }
  }
  
  @Override
  public void tick(float frameTime)
  {
    if (_graphics._pApplet.mousePressed)
    {
      long currentTime = Calendar.getInstance().getTimeInMillis();
      if ((currentTime - _timeOfLastMouseLeft) > WAIT_BETW_CLICKS
          && _graphics._pApplet.mouseButton == PConstants.LEFT)
      {
        _timeOfLastMouseLeft = currentTime;
        zoomIn();
      }
      if (_graphics._pApplet.mouseButton == PConstants.RIGHT)
      {
        zoomOut();
      }
    }
    
    if (_graphics.drawCommentIfMouseOver())
    {
      for (int i = 0; i < _nextItemIdx; i++)
      {
        _itemGraphicsToDrawComments[i].drawMouseOver();
      }
      _nextItemIdx = 0;
    }
  }
  
  private void zoomOut()
  {
  	_graphics.zoomOut(_graphics.getMouseCoordX(), _graphics.getMouseCoordY());
  }

//  private void zoomReset()
//  {
//    _graphics.zoomReset();
//  }

  private void zoomIn()
  {
    _graphics.zoomIn(_graphics.getMouseCoordX(), _graphics.getMouseCoordY());
  }

}
