package jgv.item;


public interface IItemForItemGraphics<T extends JGVItemGraphics,
    U extends JGVItemPhysics>
{

  public String getLabel();
  
  public String getComment();
  
  public U getItemPhysics();
  
  public T getItemGraphics();
  
}
