package jgv.item;


public interface IItemForItemPhysics<T extends JGVItemGraphics,
    U extends JGVItemPhysics>
{

  public T getItemGraphics();
  
  public U getItemPhysics();
  
}
