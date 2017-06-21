package jgv.physics;

import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.ParticleBehavior2D;

public interface IPhysicsForItems
{

  public void addBehavior(ParticleBehavior2D behavior);
  
  public void addParticle(VerletParticle2D particle);
  
  public void addSpring(VerletSpring2D spring);
  
  public void removeBehavior(ParticleBehavior2D behaviour);
  
  public void removeParticle(VerletParticle2D particle);
  
  public void removeSpring(VerletSpring2D spring);
  
}
