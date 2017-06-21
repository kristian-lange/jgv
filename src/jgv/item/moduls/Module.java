package jgv.item.moduls;

import jgv.item.JGVItemGraphics;
import jgv.item.JGVItemPhysics;
import jgv.item.JGVItem;

public abstract class Module
{
	
	private JGVItem<? extends JGVItemGraphics, ? extends JGVItemPhysics> _item;

	public void setItem(
	    JGVItem<? extends JGVItemGraphics, ? extends JGVItemPhysics> item)
	{
		_item = item;
	}
	
	public JGVItem<? extends JGVItemGraphics, ? extends JGVItemPhysics> getItem()
	{
		return _item;
	}

	public abstract void setup();
	
  // Cleanup before removing
  public abstract void cleanup();
  
}
