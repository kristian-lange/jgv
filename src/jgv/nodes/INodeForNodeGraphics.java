package jgv.nodes;

import jgv.item.IItemForItemGraphics;
import jgv.nodes.graphics.JGVNodeGraphics;
import jgv.nodes.physics.JGVNodePhysics;

public interface INodeForNodeGraphics 
    extends IItemForItemGraphics<JGVNodeGraphics, JGVNodePhysics>
{
  
  public JGVNodeGraphics getNodeGraphics();
  
  public JGVNodePhysics getNodePhysics();

}
