package jgv.nodes.physics;

import jgv.nodes.INodeForNodePhysics;
import jgv.physics.Coordinates;
import jgv.physics.JGVPhysics;
import toxi.physics2d.behaviors.AttractionBehavior;

public class NPJelly extends JGVNodePhysics
{

  private static final float BEHAVIOR_STRENGTH_DEF = -0.1f;
  private static final float BEHAVIOR_RADIUS_DEF = 0.15f;
  private float _behaviorStrength = BEHAVIOR_STRENGTH_DEF;
  private float _behaviorRadius = BEHAVIOR_RADIUS_DEF;
  
  private AttractionBehavior _behavior;
  
  public NPJelly(Coordinates coords, float attractionStrenght, float attractionRadius)
  {
    super(coords);
    _behaviorStrength = -attractionStrenght;
    _behaviorRadius = attractionRadius;
  }
  
  public NPJelly(Coordinates coords)
  {
    super(coords);
  }
  
  public NPJelly()
  {
  	super();
  }
  
  @Override
  public void setup(INodeForNodePhysics node, JGVPhysics physics)
  {
    super.setup(node, physics);
    _behavior = new AttractionBehavior(getParticle(), _behaviorRadius, _behaviorStrength);
    _physics.addBehavior(_behavior);
  }

  @Override
  public void cleanup()
  {
    super.cleanup();
    _physics.removeBehavior(_behavior);
  }

}
