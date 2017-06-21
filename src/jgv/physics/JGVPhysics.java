package jgv.physics;

import jgv.graphics.IGraphicsForPhysics;
import jgv.interfaces.IStatusTextable;
import jgv.interfaces.ITickable;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.ParticleBehavior2D;

public abstract class JGVPhysics implements IPhysicsForGraphics, 
    IPhysicsForItems, IStatusTextable, ITickable
{
  
  private VerletPhysics2D _particleSystem = new VerletPhysics2D();
  private IGraphicsForPhysics _graphicsWrapper;
  
  public void setup(IGraphicsForPhysics iGraphicsWrapper)
  {
    _graphicsWrapper = iGraphicsWrapper;
  }
  
  protected IGraphicsForPhysics getGraphicsWrapper()
  {
    return _graphicsWrapper;
  }
  
  protected VerletPhysics2D getParticleSystem()
  {
    return _particleSystem;
  }
  
  public void addBehavior(ParticleBehavior2D behavior)
  {
    synchronized(_particleSystem)
    {
      _particleSystem.addBehavior(behavior);
    }
  }

  public void addParticle(VerletParticle2D particle)
  {
    synchronized(_particleSystem)
    {
      _particleSystem.addParticle(particle);
    }
  }

  public void addSpring(VerletSpring2D spring)
  {
    synchronized(_particleSystem)
    {
      _particleSystem.addSpring(spring);
    }
  }

  public String getStatusText()
  {
    return "" +
        _particleSystem.particles.size() + " particles\n" +
        _particleSystem.springs.size() + " springs\n" +
        _particleSystem.behaviors.size() + " behaviors\n";
  }

  public void removeBehavior(ParticleBehavior2D behaviour)
  {
    synchronized(_particleSystem)
    {
      _particleSystem.removeBehavior(behaviour);
    }
  }

  public void removeParticle(VerletParticle2D particle)
  {
    synchronized(_particleSystem)
    {
      _particleSystem.removeParticle(particle);
    }
  }

  public void removeSpring(VerletSpring2D spring)
  {
    synchronized(_particleSystem)
    {
      _particleSystem.removeSpring(spring);
    }
  }

  public void tick(float frameTime)
  {
    synchronized(_particleSystem)
    {
    	_particleSystem.setTimeStep(frameTime);
      _particleSystem.update();
    }
  }
  
  public float[] getWorldCoords()
  {
    float xMax = Float.NEGATIVE_INFINITY;
    float xMin = Float.POSITIVE_INFINITY;
    float yMax = Float.NEGATIVE_INFINITY;
    float yMin = Float.POSITIVE_INFINITY;

    // Determines the minimum and maximum coordinates of all particles
    synchronized (getParticleSystem())
    {
      for (VerletParticle2D particle : getParticleSystem().particles)
      {
        xMin = Math.min(xMin, particle.x);
        xMax = Math.max(xMax, particle.x);
        yMin = Math.min(yMin, particle.y);
        yMax = Math.max(yMax, particle.y);
      }
    }
    float[] containerCords = {xMin, xMax, yMin, yMax};
    return containerCords;
  }

}
