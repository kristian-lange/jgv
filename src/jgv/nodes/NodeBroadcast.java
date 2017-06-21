package jgv.nodes;

public class NodeBroadcast extends JGVNode
{

  public NodeBroadcast(int id)
  {
    super(id, null, null);
  }
  
  public NodeBroadcast()
  {
    super(RANDOM.nextInt(), null, null);
  }
  
  @Override
  public String toString()
  {
    return getLabel();
  }

}
