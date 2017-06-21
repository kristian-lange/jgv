package jgv.edges;

import jgv.edges.graphics.JGVEdgeGraphics;
import jgv.edges.physics.JGVEdgePhysics;
import jgv.item.JGVItem;
import jgv.nodes.JGVNode;

public abstract class JGVEdge extends JGVItem<JGVEdgeGraphics, JGVEdgePhysics>
    implements IEdgeForEdgeGraphics, IEdgeForEdgePhysics
{
  
  protected JGVNode _sourceNode;
  protected JGVNode _targetNode;
  
  public JGVEdge(int id, JGVNode sourceNode, JGVNode targetNode,
      JGVEdgeGraphics jGVEdgeGraphics, JGVEdgePhysics jGVEdgePhysics)
  {
    super(id, jGVEdgeGraphics, jGVEdgePhysics);
    _sourceNode = sourceNode;
    _targetNode = targetNode;
  }
  
  @Override
  public JGVEdgePhysics getEdgePhysics()
  {
    return getItemPhysics();
  }
  
  @Override
  public JGVEdgeGraphics getEdgeGraphics()
  {
    return getItemGraphics();
  }
  
  public JGVNode getSourceNode()
  {
    return _sourceNode;
  }
  
  public JGVNode getTargetNode()
  {
    return _targetNode;
  }
  
  public boolean hasSourceNode(JGVNode jGVNode)
  {
    return _sourceNode.equals(jGVNode);
  }
  
  public boolean hasTargetNode(JGVNode jGVNode)
  {
    return _targetNode.equals(jGVNode);
  }
  
  public boolean hasNode(JGVNode jGVNode)
  {
    return (_sourceNode.equals(jGVNode) || _targetNode.equals(jGVNode));
  }
  
  public void changeEdgeGraphics(JGVEdgeGraphics newEdgeGraphics)
  {
    if (getEdgeGraphics() != null)
    {
      getEdgeGraphics().cleanup();
      newEdgeGraphics.setup(getEdgeGraphics());
    }
    changeItemGraphics(newEdgeGraphics);
  }
  
}
