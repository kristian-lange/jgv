package jgv.nodes.physics;

import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;
import jgv.nodes.INodeForNodePhysics;
import jgv.physics.Coordinates;
import jgv.physics.JGVPhysics;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;

public class NPQuake extends JGVNodePhysics implements ITickable, IBounceable
{
  
  private static final float SPRING_LENGTH = 0.10f;
  private static final float SPRING_STRENGTH = 0.01f;

  private VerletParticle2D _anchor;
  private VerletSpring2D _spring;
  private float _springLength = SPRING_LENGTH;
  private float _springStrength = SPRING_STRENGTH;

  public NPQuake(Coordinates coords, float springLength, float springStrength)
  {
  	super(coords.cloneAndJitter());
    _anchor = new VerletParticle2D(coords.getX(), coords.getY());
    _anchor.lock();
    _springLength = springLength;
    _springStrength = springStrength;
  }
  
  public NPQuake(Coordinates coords)
  {
    this(coords, SPRING_LENGTH, SPRING_STRENGTH);
  }
  
  @Override
  public void setup(INodeForNodePhysics node, JGVPhysics physics)
  {
    super.setup(node, physics);
    _spring = new VerletSpring2D(getParticle(), _anchor, _springLength, _springStrength);
    _physics.addSpring(_spring);
  }
  
  @Override
  public void changeCoords(Coordinates coords)
  {
  	_anchor.set(coords.getX(), coords.getY());
  }

  @Override
  public void cleanup()
  {
    super.cleanup();
    _physics.removeSpring(_spring);
  }
  
  @Override
  public void bounce()
  {
    getParticle().jitter(0.01f);
  }

  @Override
  public void tick(float frameTime)
  {
    // nothing to do
  }
  
}
