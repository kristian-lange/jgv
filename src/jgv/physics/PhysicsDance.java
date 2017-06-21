package jgv.physics;

import jgv.JGVConstants;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;

public class PhysicsDance extends JGVPhysics
{
  private static final float WORLD_DRAG = 0.05f;
  private static final VerletParticle2D ANCHOR = 
    new VerletParticle2D(new Vec2D(JGVConstants.CENTER_X, JGVConstants.CENTER_Y));
  
  public PhysicsDance()
  {
    getParticleSystem().setDrag(WORLD_DRAG);
    ANCHOR.lock();
    addParticle(ANCHOR);
  }
  
  public static VerletParticle2D getAnchor()
  {
    return ANCHOR;
  }

}
