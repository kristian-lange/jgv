package jgv.edges.physics;

import jgv.edges.IEdgeForEdgePhysics;
import jgv.edges.JGVEdge;
import jgv.item.JGVItemPhysics;
import jgv.physics.JGVPhysics;
import toxi.physics2d.VerletParticle2D;

public abstract class JGVEdgePhysics extends JGVItemPhysics
{
  
  protected IEdgeForEdgePhysics _edge;
  
  public void setup(JGVEdge edge, JGVPhysics physics)
  {
    super.setup(edge, physics);
    _edge = edge;
  }
  
  public VerletParticle2D getParticleSource()
  {
  	return _edge.getSourceNode().getItemPhysics().getParticle();
  }

  public VerletParticle2D getParticleTarget()
  {
  	return _edge.getTargetNode().getItemPhysics().getParticle();
  }
  
  public float getSourceX()
  {
    return getParticleSource().x();
  }
  
  public float getSourceY()
  {
    return getParticleSource().y();
  }
  
  public float getSourceVelocityX()
  {
    return getParticleSource().getVelocity().x();
  }
  
  public float getSourceVelocityY()
  {
    return getParticleSource().getVelocity().y();
  }
  
  public float getTargetX()
  {
    return getParticleTarget().x();
  }
  
  public float getTargetY()
  {
    return getParticleTarget().y();
  }
  
  public float getTargetVelocityX()
  {
    return getParticleTarget().getVelocity().x();
  }
  
  public float getTargetVelocityY()
  {
    return getParticleTarget().getVelocity().y();
  }
  
}
