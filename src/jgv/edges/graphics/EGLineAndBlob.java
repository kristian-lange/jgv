package jgv.edges.graphics;

import jgv.JGVConstants;
import jgv.graphics.Color;
import jgv.interfaces.IBounceable;

public class EGLineAndBlob extends EGLine implements IBounceable
{
	
	private static final float BLOB_RADIUS_DEF = 10;
	private static final float BLOB_STEP_DEF = 0.05f;
  private final float BLOB_RADIUS;
  private final float BLOB_STEP;
  private final float BLOB_START = 0;
  private float _blobLocation = BLOB_START;

  public EGLineAndBlob(boolean labelVisibility, float strokeWeight,
  				Color strokeColor, Color textColor, float blobRadius, float blobStep)
  {
    super(labelVisibility, strokeWeight, strokeColor, textColor);
    BLOB_RADIUS = blobRadius;
    BLOB_STEP = blobStep;
  }
  
  public EGLineAndBlob(float strokeWeight, Color color, float blobRadius, float blobStep)
  {
    this(JGVConstants.EDGE_LABEL_VISIBILITY, strokeWeight, color,
    		JGVConstants.TEXT_COLOR, blobRadius, blobStep);
  }
  
  public EGLineAndBlob(float strokeWeight)
  {
    this(JGVConstants.EDGE_LABEL_VISIBILITY, strokeWeight,
    		JGVConstants.EDGE_STROKE_COLOR,	JGVConstants.TEXT_COLOR,
    		BLOB_RADIUS_DEF, BLOB_STEP_DEF);
  }
  
  public EGLineAndBlob()
  {
    this(JGVConstants.EDGE_LABEL_VISIBILITY, JGVConstants.EDGE_STROKE_WEIGHT,
    		JGVConstants.EDGE_STROKE_COLOR, JGVConstants.TEXT_COLOR, 
    		BLOB_RADIUS_DEF, BLOB_STEP_DEF);
  }

  @Override
  protected void drawEdge()
  {
    _graphics._pApplet.line(_xCoordSource, _yCoordSource,
        _xCoordTarget, _yCoordTarget);
    drawBlob();
  }

  private void drawBlob()
  {
    if (_blobLocation <= 1.0f)
    {
      _blobLocation += BLOB_STEP;
      int movingPointX = (int) ((_xCoordTarget - _xCoordSource) 
          * _blobLocation + _xCoordSource);
      int movingPointY = (int) ((_yCoordTarget - _yCoordSource) 
          * _blobLocation + _yCoordSource);

      _graphics._pApplet.noStroke();
      int[] strokeColor = _strokeColor.getAsArray();
      _graphics._pApplet.fill(strokeColor[0], strokeColor[1], 
          strokeColor[2], strokeColor[3]);
      _graphics._pApplet.ellipse(movingPointX, movingPointY,
          BLOB_RADIUS, BLOB_RADIUS);
    }
  }
  
  @Override
  public void bounce()
  {
    _blobLocation = BLOB_START;
  }

}
