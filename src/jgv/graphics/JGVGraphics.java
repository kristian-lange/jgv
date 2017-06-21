package jgv.graphics;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.List;
import jgv.JGVConstants;
import jgv.graph.Ticker;
import jgv.input.KeyboardHandler;
import jgv.interfaces.IStatusTextable;
import jgv.interfaces.ITickable;
import jgv.physics.IPhysicsForGraphics;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PShape;
import toxi.math.SigmoidInterpolation;

public class JGVGraphics implements IGraphicsForPhysics, IGraphicsForItems,
    IStatusTextable, ITickable
{
  
  private static final int BACKGROUND_COLOR = 255;
  private static final String FONT_FILENAME = "ArialMT-48.vlw";
  private static final int FONT_SIZE = 12;
  private static final int GEOM2D_MODE = PConstants.CENTER;
  private static final float WORLD_BORDER_MARGIN = 0.1f;
  private static final float ZOOM_FACTOR = 2f;
  private static final float ZOOM_INTERPOL_STEP = 0.05f;
  private static final SigmoidInterpolation SIGMOID_INTERPOLATION = 
  		new SigmoidInterpolation();
  
  public GraphVisuApplet _pApplet;
  private Ticker _ticker;
  private KeyboardHandler _keyboardHandler;
  private float _frameRate = JGVConstants.FRAME_RATE;
  private IPhysicsForGraphics _physics;
  private StatusTexter _statusTexter;
  private List<PShape> _pShapeList = new ArrayList<PShape>();
  private List<PFont> _fontList = new ArrayList<PFont>();
  private boolean _drawStatusText = true;
  private boolean _drawCommentIfMouseOver = true;
  private boolean _loop = true;
  private boolean _autoscale = false;
  private float _scale = 1.0f;
  private float _scaleTarget = _scale;
  private float _zoomInterpolFactor = 0.0f;
  private float _zoomCoordX = 0.0f;
  private float _zoomCoordY = 0.0f;
  private float _zoomCoordXTarget = _zoomCoordX;
  private float _zoomCoordYTarget = _zoomCoordY;
  
  public JGVGraphics(boolean autoscale)
  {
    _autoscale = autoscale;
  }
  
  public class GraphVisuApplet extends PApplet
  {
    
    private static final long serialVersionUID = -614358754466241611L;
    
    private int _appletWidth;
    private int _appletHeight;
    private boolean _isSetup = false;
    
    private GraphVisuApplet(int width, int height)
    {
      _appletWidth = width;
      _appletHeight = height;
    }
    
    @Override
    public void setup()
    {
      if (!_isSetup )
      {
        size(_appletWidth, _appletHeight, P2D);//PConstants.OPENGL);
        _isSetup = true;
      }
      PFont font = loadFont(FONT_FILENAME);
      textFont(font, FONT_SIZE); 
      smooth();
      frameRate(_frameRate);
      ellipseMode(GEOM2D_MODE);
      rectMode(GEOM2D_MODE);
      shapeMode(GEOM2D_MODE);
    }
    
    @Override
    public void draw()
    {
      float frameTime = 1 / super.frameRate;
      _ticker.tick(frameTime);
    }
    
    @Override
    public void keyPressed()
    {
      _keyboardHandler.keyPressed();
    }
    
    @Override
    public void keyTyped()
    {
      _keyboardHandler.keyTyped();
    }
    
    @Override
    public void keyReleased()
    {
      _keyboardHandler.keyReleased();
    }
    
  }
  
  public Applet getApplet(int appletWidth, int appletHeigth)
  {
    if (_pApplet == null)
    {
      _pApplet = new GraphVisuApplet(appletWidth, appletHeigth);
    }
    return (Applet) _pApplet;
  }
  
  public void setup(IPhysicsForGraphics physics, StatusTexter statusTexter, 
      Ticker ticker, KeyboardHandler keyboardHandler)
  {
    _physics = physics;
    _statusTexter = statusTexter;
    _ticker = ticker;
    _keyboardHandler = keyboardHandler;
  }
  
  public void setFrameRate(float frameRate)
  {
    if (frameRate > 0)
    {
      _pApplet.frameRate(frameRate);
    }
  }
  
  protected IPhysicsForGraphics getPhysicsWrapper()
  {
    return _physics;
  }

  public void tick(float frameTime)
  {
    _pApplet.background(BACKGROUND_COLOR);
    drawStatusText();
    if (_autoscale)
    {
      zoomToWorld();
    }
    else
    {
      zoom();
    }
  }

  private void drawStatusText()
  {
    if (_drawStatusText)
    {
      _pApplet.fill(0);
      _pApplet.textAlign(PConstants.LEFT, PConstants.TOP);
      _pApplet.text(_statusTexter.getStatusText(), 10, 10);
    }
  }

  public String getStatusText()
  {
    return "" + (int) _pApplet.frameRate + " FPS";
  }
  
  public int loadPShape(String path)
  {
    PShape pShape = _pApplet.loadShape(path);
    if (!_pShapeList.contains(pShape))
    {
      _pShapeList.add(pShape);
    }
    return _pShapeList.indexOf(pShape);
  }
  
  public PShape getPShape(int pShapeIndex) throws IndexOutOfBoundsException
  {
    return _pShapeList.get(pShapeIndex);
  }
  
  public int loadFont(String path)
  {
    PFont font = _pApplet.loadFont(path);
    if (!_fontList.contains(font))
    {
      _fontList.add(font);
    }
    return _fontList.indexOf(font);
  }
  
  public void changeFont(int fontIndex) throws IndexOutOfBoundsException
  {
    _pApplet.textFont(_fontList.get(fontIndex));
  }
  
  public void changeFont(int fontIndex, float fontSize)
      throws IndexOutOfBoundsException
  {
    _pApplet.textFont(_fontList.get(fontIndex), fontSize);
  }

  public void setDrawStatusText(boolean drawStatusText)
  {
    _drawStatusText = drawStatusText;
  }
  
  public int getFontSize()
  {
    return FONT_SIZE;
  }
  
  private void zoomToWorld()
  {
    float[] universeCoords = getPhysicsWrapper().getWorldCoords();
    float xMin = universeCoords[0];
    float xMax = universeCoords[1];
    float yMin = universeCoords[2];
    float yMax = universeCoords[3];
    
    float deltaX = xMax - xMin;
    float deltaY = yMax - yMin;

    // The centroid coordinates are in the center of all nodes
    float centroidX = xMin + 0.5f * deltaX;
    float centroidY = yMin + 0.5f * deltaY;

    float scale;
    if (deltaY > deltaX)
    {
      scale = 1 / (deltaY + WORLD_BORDER_MARGIN);
    }
    else
    {
      scale = 1 / (deltaX + WORLD_BORDER_MARGIN);
    }
    
    _pApplet.translate(_pApplet.width * 0.5f, _pApplet.height * 0.5f);
    _pApplet.scale(scale);
    _pApplet.translate(_pApplet.width * -centroidX,
        _pApplet.height * -centroidY);
  }
  
  private void zoom()
  {
    if (_zoomInterpolFactor <= 1)
    {
      _scale = SIGMOID_INTERPOLATION.interpolate(_scale, _scaleTarget,
          _zoomInterpolFactor);
      _zoomCoordX = SIGMOID_INTERPOLATION.interpolate(
          _zoomCoordX, _zoomCoordXTarget, _zoomInterpolFactor);
      _zoomCoordY = SIGMOID_INTERPOLATION.interpolate(
          _zoomCoordY, _zoomCoordYTarget, _zoomInterpolFactor);
      _zoomInterpolFactor += ZOOM_INTERPOL_STEP;
    }
    _pApplet.translate(_zoomCoordX, _zoomCoordY);
    _pApplet.scale(_scale);
    _pApplet.translate(-_zoomCoordX, -_zoomCoordY);
  }
  
  public void zoomIn(float xCoord, float yCoord)
  {
    _zoomCoordXTarget = xCoord;
    _zoomCoordYTarget = yCoord;
    _scaleTarget = _scale * ZOOM_FACTOR;
    _zoomInterpolFactor = 0.0f;
  }
  
  public void zoomOut(float xCoord, float yCoord)
  {
    _zoomCoordXTarget = xCoord;
    _zoomCoordYTarget = yCoord;
    _scaleTarget = _scale / ZOOM_FACTOR;
    _zoomInterpolFactor = 0.0f;
  }
  
  public void zoomReset()
  {
    _zoomCoordXTarget = 0.0f;
    _zoomCoordYTarget = 0.0f;
    _scaleTarget = 1.0f;
    _zoomInterpolFactor = 0.0f;
  }
  
  public float getMouseCoordX()
  {
    return (_pApplet.mouseX - _zoomCoordX) /_scale + _zoomCoordX;
  }
  
  public float getMouseCoordY()
  {
    return (_pApplet.mouseY - _zoomCoordY) /_scale + _zoomCoordY;
  }

  public void switchLoop()
  {
    if (_loop )
    {
      noLoop();
    }
    else 
    {
      loop();
    }
    _loop = !_loop;
  }
  
  public void noLoop()
  {
  	_pApplet.noLoop();
  }
  
  public void loop()
  {
  	_pApplet.loop();
  }
  
  public boolean drawCommentIfMouseOver()
  {
    return _drawCommentIfMouseOver;
  }
  
  public void setDrawCommentWhenMouseOver(boolean dcwmo)
  {
    _drawCommentIfMouseOver = dcwmo;
  }
  
  public void switchDrawCommentWhenMouseOver()
  {
    _drawCommentIfMouseOver = !_drawCommentIfMouseOver;
  }

  @Override
  public int getWidth()
  {
    return _pApplet.width;
  }

  @Override
  public int getHeight()
  {
    return _pApplet.height;
  }
  
}