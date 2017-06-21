package jgv.graph;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

import jgv.Logger;
import jgv.edges.JGVEdge;
import jgv.edges.graphics.JGVEdgeGraphics;
import jgv.edges.physics.JGVEdgePhysics;
import jgv.graphics.JGVGraphics;
import jgv.graphics.StatusTexter;
import jgv.input.KeyboardHandler;
import jgv.input.MouseHandler;
import jgv.interfaces.IStatusTextable;
import jgv.interfaces.ITickable;
import jgv.item.JGVItemGraphics;
import jgv.item.JGVItemPhysics;
import jgv.item.JGVItem;
import jgv.item.moduls.Module;
import jgv.nodes.JGVNode;
import jgv.nodes.graphics.JGVNodeGraphics;
import jgv.nodes.physics.JGVNodePhysics;
import jgv.physics.JGVPhysics;

/**
 * @author Kristian Lange
 *
 */
public class JGVGraph implements ITickable, IStatusTextable
{
  
  public final int MAX_EDGES;
  public final int MAX_NODES;
  
  private ConcurrentMap<Integer, JGVEdge> _edges;
  private ConcurrentMap<Integer, JGVNode> _nodes;
  private JGVGraphics _graphics;
  private JGVPhysics _physics;
  private Ticker _ticker;
  private MouseHandler _mouseHandler;
  private KeyboardHandler _keyboardHandler;
  
  public JGVGraph(JGVGraphics graphics, JGVPhysics physics,
      int maxNumberOfEdges, int maxNumberOfNodes)
  {
    MAX_EDGES = maxNumberOfEdges;
    MAX_NODES = maxNumberOfNodes;
    
    _graphics = graphics;
    _physics = physics;
    _edges = new ConcurrentLinkedHashMap.Builder<Integer, JGVEdge>()
        .maximumWeightedCapacity(maxNumberOfEdges)
        .build();
    _nodes = new ConcurrentLinkedHashMap.Builder<Integer, JGVNode>()
        .maximumWeightedCapacity(maxNumberOfNodes)
        .build();
    _mouseHandler = new MouseHandler(graphics);
    _keyboardHandler = new KeyboardHandler(graphics);
    
    _ticker = new Ticker();
    _ticker.add(graphics);
    _ticker.add(physics);
    _ticker.add(this);
    _ticker.add(_mouseHandler);
    
    StatusTexter statusTexter = new StatusTexter();
    statusTexter.addStatusTextable(this);
    statusTexter.addStatusTextable(physics);
    statusTexter.addStatusTextable(graphics);
    
    physics.setup(graphics);
    graphics.setup(physics, statusTexter, _ticker, _keyboardHandler);
  }
  
  protected void add(
      JGVItem<? extends JGVItemGraphics, ? extends JGVItemPhysics> item)
  {
    if (item instanceof JGVEdge)
    {
      addEdge((JGVEdge) item);
    }
    if (item instanceof JGVNode)
    {
      addNode((JGVNode) item);
    }
  }
  
  protected void addNode(JGVNode node)
  {
    constructNode(node);
    _nodes.put(node.getId(), node);
//    Logger.getLogger().log(Level.INFO, "add node " + node.toString());
  }

  protected void addEdge(JGVEdge edge)
  {
    edge.getSourceNode().addEdge(edge);
    constructEdge(edge);
    _edges.put(edge.getId(), edge);
//    Logger.getLogger().log(Level.INFO, "add edge " + edge.toString());
  }
  
  private void constructNode(JGVNode node)
  {
    JGVNodeGraphics jGVNodeGraphics = node.getNodeGraphics();
    JGVNodePhysics jGVNodePhysics = node.getNodePhysics();
    
    if (jGVNodeGraphics != null)
    {
      jGVNodeGraphics.setup(node, _graphics, _mouseHandler);
    }
    if (jGVNodePhysics != null)
    {
      jGVNodePhysics.setup(node, _physics);
    }
  }

//  private <T extends JGVItemGraphics, U extends JGVItemPhysics> void constructItem(
//      JGVItem<T, U> item)
//  {
//    T itemGraphics = item.getItemGraphics();
//    U itemPhysics = item.getItemPhysics();
//    
//    if (itemGraphics != null)
//    {
//      itemGraphics.setup(item, _graphics, _mouseHandler);
//    }
//    if (itemPhysics != null)
//    {
//      itemPhysics.setup(item, _physics);
//    }
//  }
  
  private void constructEdge(JGVEdge edge)
  {
    JGVEdgeGraphics jGVEdgeGraphics = edge.getEdgeGraphics();
    JGVEdgePhysics jGVEdgePhysics = edge.getEdgePhysics();
    
    if (jGVEdgeGraphics != null)
    {
      jGVEdgeGraphics.setup(edge, _graphics, _mouseHandler);
    }
    if (jGVEdgePhysics != null)
    {
      jGVEdgePhysics.setup(edge, _physics);
    }
  }
  
  protected void removeNode(int id)
  {
    JGVNode nodeToRemove = _nodes.get(id);
    if (nodeToRemove != null)
    {
      removeNode(nodeToRemove);
    }
  }

  private void removeNode(JGVNode node)
  {
    _nodes.remove(node.getId());
    destructItem(node);
    //    Logger.getLogger().log(Level.INFO, "removed node "
    //        + removedNode.toString());
    // We must remove all node's edges
    for (JGVEdge edgeToRemove : node.getEdges())
    {
      _edges.remove(edgeToRemove.getId());
      //      Logger.getLogger().log(Level.INFO, "removed edge " 
      //          + edgeToRemove.toString());
    }
  }
  
  protected void removeEdge(int id)
  {
    JGVEdge edgeToRemove = _edges.get(id);
    if (edgeToRemove != null)
    {
      removeEdge(edgeToRemove);
    }
  }
  
  private void removeEdge(JGVEdge edge)
  {
    _edges.remove(edge.getId());
    destructItem(edge);
    // We must remove the edge's entry in it's source node
    edge.getSourceNode().removeEdge(edge);
//    Logger.getLogger().log(Level.INFO, "removed edge " 
//        + removedEdge.toString());
  }
  
  private void destructItem(JGVItem<?, ?> item)
  {
    item.getItemGraphics().cleanup();
    item.getItemPhysics().cleanup();
    for (Module module : item.getModuls())
    {
      if (module == null)
      {
        break;
      }
      module.cleanup();
    }
  }
  
  protected void removeAllEdges()
  {
    for (JGVEdge edge : _edges.values())
    {
      removeEdge(edge);
    }
  }
  
  protected void removeAllNodes()
  {
    for (JGVNode node : _nodes.values())
    {
      removeNode(node);
    }
  }
  
  protected void cleanGraph()
  {
    // TODO check if we can just clear or have to remove every single item
    _edges.clear();
    _nodes.clear();
  }
  
  protected JGVEdge getEdge(int id)
  {
    return _edges.get(id);
  }
  
  protected JGVNode getNode(int id)
  {
    return _nodes.get(id);
  }
  
  /**
   * @param sourceNode
   * @param targetNode
   * @return Returns an array of edges that directs from sourceNode
   * to targetNode. The array is empty if there is none.  
   */
  protected JGVEdge[] getDirectedEdges(JGVNode sourceNode, JGVNode targetNode)
  {
    ArrayList<JGVEdge> directedEdges = new ArrayList<JGVEdge>();
    for (JGVEdge edge : sourceNode.getEdges())
    {
      if (edge.hasTargetNode(targetNode))
      {
        directedEdges.add(edge);
      }
    }
    return (JGVEdge[]) directedEdges.toArray(new JGVEdge[directedEdges.size()]);
  }
  
  /**
   * @param node1
   * @param node2
   * @return Returns an array of edges that connects node1 and node2.
   * The direction of the edge doesn't matter. The array is empty
   * if there is none. 
   */
  protected JGVEdge[] getUndirectedEdges(JGVNode node1, JGVNode node2)
  {
    ArrayList<JGVEdge> undirectedEdges = new ArrayList<JGVEdge>();
    for (JGVEdge edge : node1.getEdges())
    {
      if (edge.hasTargetNode(node2))
      {
        undirectedEdges.add(edge);
      }
    }
    for (JGVEdge edge : node2.getEdges())
    {
      if (edge.hasTargetNode(node1))
      {
        undirectedEdges.add(edge);
      }
    }
    return (JGVEdge[]) undirectedEdges.toArray(
        new JGVEdge[undirectedEdges.size()]);
  }
  
  protected void setLogging(boolean logging)
  {
    Logger.getLogger().setLogging(logging);
  }
  
  public Applet getApplet(int width, int height)
  {
    return _graphics.getApplet(width, height);
  }
  
  @Override
  public void tick(float frameTime)
  {
    // iterate through _edges and _nodes (tickGraphItems method from Ticker)
    // and call Ticker's tickItem
    for (JGVEdge edge : _edges.values())
    {
      if (edge.isRetired())
      {
        removeEdge(edge);
        continue;
      }
      _ticker.tickItem(frameTime, edge);
    }

    for (JGVNode node : _nodes.values())
    {
      if (node.isRetired())
      {
        removeNode(node);
        continue;
      }
      _ticker.tickItem(frameTime, node);
    }
  }
  
  @Override
  public String getStatusText()
  {
    return "" + _nodes.size() + " nodes\n" + _edges.size() + " edges\n";
  }

}
