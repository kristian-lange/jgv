package jgv.edges;

import jgv.edges.graphics.JGVEdgeGraphics;
import jgv.edges.physics.JGVEdgePhysics;
import jgv.item.IItemForItemGraphics;

public interface IEdgeForEdgeGraphics
    extends IItemForItemGraphics<JGVEdgeGraphics, JGVEdgePhysics>
{

  public JGVEdgePhysics getEdgePhysics();
  
  public JGVEdgeGraphics getEdgeGraphics();
  
}
