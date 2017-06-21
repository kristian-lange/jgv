package jgv.nodes;

import jgv.item.IItemForItemPhysics;
import jgv.nodes.graphics.JGVNodeGraphics;
import jgv.nodes.physics.JGVNodePhysics;

public interface INodeForNodePhysics 
    extends IItemForItemPhysics<JGVNodeGraphics, JGVNodePhysics>
{
  
  public JGVNodeGraphics getNodeGraphics();
  
  public JGVNodePhysics getNodePhysics();
  
}
