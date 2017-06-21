package jgv.item;

import jgv.physics.JGVPhysics;

public abstract class JGVItemPhysics
{
  
  protected IItemForItemPhysics<? extends JGVItemGraphics , ? extends JGVItemPhysics> _item;
  protected JGVPhysics _physics;
  
  public void setup(
      IItemForItemPhysics<? extends JGVItemGraphics , ? extends JGVItemPhysics> item,
      JGVPhysics physics)
  {
    _item = item;
    _physics = physics;
  }
  
  public void setup(JGVItemPhysics oldItemPhysics)
  {
    _item = oldItemPhysics._item;
    _physics = oldItemPhysics._physics;
  }
  
  public abstract void cleanup();
  
}
