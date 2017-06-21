package jgv.graphics;

public class Color
{
  private int[] _color = new int[4];

  public Color(int red, int green, int blue)
  {
    _color[0] = red;
    _color[1] = green;
    _color[2] = blue;
    _color[3] = 255;
  }
  
  public Color(int red, int green, int blue, int alpha)
  {
    this(red, green, blue);
    _color[3] = alpha;
  }
  
  public Color(int gray)
  {
    _color[0] = gray;
    _color[1] = gray;
    _color[2] = gray;
    _color[3] = 255;
  }
  
  public Color clone()
  {
    return new Color(_color[0], _color[1], _color[2], _color[3]);
  }
  
  public Color(int gray, int alpha)
  {
    this(gray);
    _color[3] = alpha;
  }
  
  public int[] getAsArray()
  {
    return _color; 
  }
  
  public void setAlpha(int alpha)
  {
    _color[3] = alpha;
  }
  
  public int getAlpha()
  {
    return _color[3];
  }

}
