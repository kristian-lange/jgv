package jgv.edges;

import jgv.JGVConstants;
import jgv.edges.graphics.JGVEdgeGraphics;
import jgv.edges.physics.JGVEdgePhysics;
import jgv.nodes.JGVNode;

public class EdgeStd extends JGVEdge
{
  
  public EdgeStd(int id, JGVNode sourceNode, JGVNode targetNode,
      JGVEdgeGraphics jGVEdgeGraphics, JGVEdgePhysics jGVEdgePhysics)
  {
    super(id, sourceNode, targetNode, jGVEdgeGraphics, jGVEdgePhysics);
    _maxIdleTime = JGVConstants.EDGE_MAX_IDLE_TIME;
  }

  public EdgeStd(JGVNode sourceNode, JGVNode targetNode,
      JGVEdgeGraphics jGVEdgeGraphics, JGVEdgePhysics jGVEdgePhysics)
  {
    this(RANDOM.nextInt(), sourceNode, targetNode, jGVEdgeGraphics, jGVEdgePhysics);
  }

}