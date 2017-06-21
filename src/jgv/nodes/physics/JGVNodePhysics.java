package jgv.nodes.physics;

import jgv.item.JGVItemPhysics;
import jgv.nodes.INodeForNodePhysics;
import jgv.physics.Coordinates;
import jgv.physics.JGVPhysics;
import toxi.physics2d.VerletParticle2D;

public abstract class JGVNodePhysics extends JGVItemPhysics
{
  
  protected INodeForNodePhysics _node;
  private VerletParticle2D _particle;

  protected JGVNodePhysics(Coordinates coords)
  {
    _particle = new VerletParticle2D(coords.getX(), coords.getY());
  }
  
  protected JGVNodePhysics()
  {
  	this(Coordinates.getRandomCoords());
  }
  
  public void setup(INodeForNodePhysics node, JGVPhysics physics)
  {
    super.setup(node, physics);
    _node = node;
    _physics.addParticle(_particle);
  }
  
  public VerletParticle2D getParticle()
  {
    return _particle;
  }
  
  public float getX()
  {
    return _particle.x;
  }

  public float getY()
  {
    return _particle.y;
  }
  
  public float getVelocityX()
  {
    return _particle.getVelocity().x;
  }
  
  public float getVelocityY()
  {
    return _particle.getVelocity().y;
  }
  
  public float getWeight()
  {
    return _particle.getWeight();
  }
  
  @Override
  public void cleanup()
  {
    _physics.removeParticle(_particle);
  }
  
  public void lock()
  {
    _particle.lock();
  }
  
  public void unlock()
  {
    _particle.unlock();
  }
  
  public void changeCoords(Coordinates coords)
  {
    _particle.set(coords.getX(), coords.getY());
  }
  
}
