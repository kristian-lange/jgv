package jgv.nodes.physics;

import jgv.interfaces.IBounceable;
import jgv.interfaces.ITickable;
import jgv.nodes.INodeForNodePhysics;
import jgv.physics.Coordinates;
import jgv.physics.JGVPhysics;
import jgv.physics.PhysicsDance;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;

public class NPDance extends JGVNodePhysics implements ITickable, IBounceable
{
  
  private static final float SPRING_LENGTH_A = 0.40f;
  private static final float SPRING_LENGTH_B = 0.30f;
  private static final float SPRING_STRENGTH = 0.05f;
  private static final int BOUNCE_COUNTER_START = 5;

  private VerletSpring2D _spring;
  private int _unbounceCounter = 0;
  private float _springLengthA = SPRING_LENGTH_A;
  private float _springLengthB = SPRING_LENGTH_B;
  private float _springStrength = SPRING_STRENGTH;

  public NPDance(Coordinates coords, float springLengthA, float springLengthB,
      float springStrength)
  {
    super(coords);
    _springLengthA = springLengthA;
    _springLengthB = springLengthB;
    _springStrength = springStrength;
  }
  
  public NPDance(Coordinates coords)
  {
    super(coords);
  }
  
  public NPDance()
  {
  	super();
  }
  
  @Override
  public void setup(INodeForNodePhysics node, JGVPhysics physics)
  {
    super.setup(node, physics);
    VerletParticle2D anchor = PhysicsDance.getAnchor();
    _spring = new VerletSpring2D(getParticle(), anchor, _springLengthA, _springStrength);
    _physics.addSpring(_spring);
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
    _spring.setRestLength(_springLengthB);
    _unbounceCounter = BOUNCE_COUNTER_START;
  }

  @Override
  public void tick(float frameTime)
  {
    unbounce();
  }
  
  private void unbounce()
  {
    if (_unbounceCounter > 0)
    {
      _unbounceCounter--;
      if (_unbounceCounter == 1)
      {
        _spring.setRestLength(_springLengthA);
      }
    }
  }
  
}
