package jgv;

import jgv.graphics.Color;

public interface JGVConstants
{
  
  // Graphics
  public static final int APPLET_WIDTH = 800;
  public static final int APPLET_HEIGHT = 600;
  public static final int FRAME_RATE = 20;
  public static final float CENTER_X = 0.5f;
  public static final float CENTER_Y = 0.5f;
  
  // Text
  public static final Color TEXT_COLOR = new Color(50, 50, 50, 255);
  
  // Node
  public static final Color NODE_FILL_COLOR = new Color(50, 50, 150, 150);
  public static final Color NODE_STROKE_COLOR = null;
  public static final float NODE_STROKE_WEIGHT = 1.0f;
  public static final float NODE_WIDTH = 6.0f;
  public static final int NODE_MAX_IDLE_TIME = -1;
  public static final boolean NODE_LABEL_VISIBILITY = false;
  
  // Edge
  public static final Color EDGE_FILL_COLOR = new Color(0, 0, 150, 150);
  public static final Color EDGE_STROKE_COLOR = new Color(0, 0, 150, 150);
  public static final float EDGE_STROKE_WEIGHT = 1.0f;
  public static final int EDGE_MAX_IDLE_TIME = -1;
  public static final boolean EDGE_ARROW_VISIBILITY = false;
  public static final boolean EDGE_LABEL_VISIBILITY = false;
  public static final int EDGE_ARROW_ANGLE = 20;
  public static final float EDGE_ARROW_SIZE = 10f;
}
