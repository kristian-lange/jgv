package jgv.nodes;

import java.util.Collection;
import java.util.LinkedHashSet;
import jgv.edges.JGVEdge;
import jgv.item.JGVItem;
import jgv.nodes.graphics.JGVNodeGraphics;
import jgv.nodes.physics.JGVNodePhysics;

public abstract class JGVNode extends JGVItem<JGVNodeGraphics, JGVNodePhysics>
    implements INodeForNodeGraphics, INodeForNodePhysics
{
  
  private LinkedHashSet<JGVEdge> _edges = new LinkedHashSet<JGVEdge>(20);
  
  public JGVNode(int id, JGVNodeGraphics jGVNodeGraphics, JGVNodePhysics jGVNodePhysics)
  {
    super(id, jGVNodeGraphics, jGVNodePhysics);
  }
  
  @Override
  public JGVNodePhysics getNodePhysics()
  {
    return getItemPhysics();
  }

  @Override
  public JGVNodeGraphics getNodeGraphics()
  {
    return getItemGraphics();
  }
  
  public void addEdge(JGVEdge edge)
  {
    _edges.add(edge);
  }
  
  public Collection<JGVEdge> getEdges()
  {
    return _edges;
  }
  
  public boolean removeEdge(JGVEdge edge)
  {
    return _edges.remove(edge);
  }
  
  public void changeNodeGraphics(JGVNodeGraphics newNodeGraphics)
  {
    if (getNodeGraphics() != null)
    {
      getNodeGraphics().cleanup();
      newNodeGraphics.setup(getNodeGraphics());
    }
    changeItemGraphics(newNodeGraphics);
  }
  
}
