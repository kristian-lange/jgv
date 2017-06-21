package jgv.edges;

import jgv.edges.graphics.JGVEdgeGraphics;
import jgv.edges.physics.JGVEdgePhysics;
import jgv.item.IItemForItemPhysics;
import jgv.nodes.INodeForNodePhysics;

public interface IEdgeForEdgePhysics 
    extends IItemForItemPhysics<JGVEdgeGraphics, JGVEdgePhysics>
{
  
  public JGVEdgePhysics getEdgePhysics();
  
  public JGVEdgeGraphics getEdgeGraphics();
  
  public INodeForNodePhysics getSourceNode();
  
  public INodeForNodePhysics getTargetNode();
  
}
