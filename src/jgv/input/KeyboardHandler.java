package jgv.input;

import jgv.graphics.JGVGraphics;

public class KeyboardHandler
{
  
  private JGVGraphics _graphics;
  
  public KeyboardHandler(JGVGraphics jGVGraphics)
  {
    _graphics = jGVGraphics;
  }
  
  public void keyPressed()
  {
    // nothing to do
  }

  public void keyTyped()
  {
    switch (_graphics._pApplet.key)
    {
    case 'p':
      _graphics.switchLoop();
      break;
    case 'P':
      _graphics.switchLoop();
      break;
    case 'c':
      _graphics.switchDrawCommentWhenMouseOver();
      break;
    case 'C':
      _graphics.switchDrawCommentWhenMouseOver();
      break;
    }
  }

  public void keyReleased()
  {
    // nothing to do
  }

}
