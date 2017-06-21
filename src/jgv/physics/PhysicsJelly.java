package jgv.physics;

import toxi.geom.Rect;

public class PhysicsJelly extends JGVPhysics
{
//  private static final float WORLD_TIMESTEP = 0.02f;
  private static final float WORLD_DRAG = 0.1f;
  private static final Rect WORLD_BOUNDS = new Rect(0, 0, 1, 1);
  
  public PhysicsJelly(boolean worldBounds)
  {
    getParticleSystem().setDrag(WORLD_DRAG);
//    getParticleSystem().setTimeStep(WORLD_TIMESTEP);
    if (worldBounds)
    {
      getParticleSystem().setWorldBounds(WORLD_BOUNDS);
    }
  }
  
  public PhysicsJelly(boolean worldBounds, float worldDrag)
  {
    getParticleSystem().setDrag(worldDrag);
//    getParticleSystem().setTimeStep(worldTimeStep);
    if (worldBounds)
    {
      getParticleSystem().setWorldBounds(WORLD_BOUNDS);
    }
  }
  
}
