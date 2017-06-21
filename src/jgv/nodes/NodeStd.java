package jgv.nodes;

import jgv.JGVConstants;
import jgv.nodes.graphics.JGVNodeGraphics;
import jgv.nodes.physics.JGVNodePhysics;

public class NodeStd extends JGVNode
{

  
  public NodeStd(int id, JGVNodeGraphics jGVNodeGraphics, JGVNodePhysics jGVNodePhysics)
	{
    super(id, jGVNodeGraphics, jGVNodePhysics);
    _maxIdleTime = JGVConstants.NODE_MAX_IDLE_TIME;
	}
  
  public NodeStd(JGVNodeGraphics jGVNodeGraphics, JGVNodePhysics jGVNodePhysics)
  {
    this(RANDOM.nextInt(), jGVNodeGraphics, jGVNodePhysics);
  }
  
}
