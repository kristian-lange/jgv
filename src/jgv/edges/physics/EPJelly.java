package jgv.edges.physics;

import jgv.edges.JGVEdge;
import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;
import jgv.physics.JGVPhysics;
import toxi.physics2d.VerletSpring2D;

public class EPJelly extends JGVEdgePhysics implements IBounceable, ITickable
{
  
  private static final float SPRING_STR_DEF = 0.1f;
  private static final float SPRING_LENGTH_DEF = 0.08f;
  private VerletSpring2D _spring;
  private float _springStrength = SPRING_STR_DEF;
  private float _springLength = SPRING_LENGTH_DEF;;
  
  public EPJelly(float springStrength, float springLength)
  {
    _springStrength = springStrength;
    _springLength = springLength;
  }
  
  public EPJelly() {}
  
  @Override
  public void setup(JGVEdge edge, JGVPhysics physics)
  {
    super.setup(edge, physics);
    _spring = new VerletSpring2D(getParticleSource(), getParticleTarget(), 
        _springLength, _springStrength);
    _physics.addSpring(_spring);
  }

  @Override
  public void cleanup()
  {
    _physics.removeSpring(_spring);
  }
  
  @Override
  public void tick(float frameTime)
  {
    // nothing to do
  }
  
  @Override
  public void bounce()
  {
    // nothing to do
  }

}
