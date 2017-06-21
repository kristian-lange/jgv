package jgv.physics;

import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.ParticleBehavior2D;

public class PhysicsBasic extends JGVPhysics
{

  @Override
  public void addBehavior(ParticleBehavior2D behavior)
  {
    // nothing to do
  }

  @Override
  public void addSpring(VerletSpring2D spring)
  {
    // nothing to do
  }
  
  @Override
  public String getStatusText()
  {
    return "" + getParticleSystem().particles.size() + " particles\n";
  }

  @Override
  public void tick(float frameTime)
  {
    // nothing to do
  }

  @Override
  public void removeBehavior(ParticleBehavior2D behavior)
  {
    // nothing to do
  }

  @Override
  public void removeSpring(VerletSpring2D spring)
  {
    // nothing to do
  }

}
