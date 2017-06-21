package jgv.physics;

import java.util.Random;

public class Coordinates
{
	
	private static final Random RANDOM = new Random();
  private float[] _coordinates = new float[2];

  public Coordinates(float x, float y)
  {
    _coordinates[0] = x;
    _coordinates[1] = y;
  }
  
  public static Coordinates getRandomCoords()
  {
  	return new Coordinates(RANDOM.nextFloat(), RANDOM.nextFloat());
  }
  
  public float[] getCoords()
  {
    return _coordinates; 
  }
  
  public float getX()
  {
    return _coordinates[0];
  }
  
  public void setX(float x)
  {
  	_coordinates[0] = x;
  }
  
  public float getY()
  {
    return _coordinates[1];
  }
  
  public void setY(float y)
  {
  	_coordinates[1] = y;
  }
  
  public Coordinates clone()
  {
  	return new Coordinates(getX(), getY());
  }
  
  public Coordinates cloneAndJitter()
  {
  	Coordinates newCoords = clone();
  	newCoords.setX((float) (_coordinates[0] + ((RANDOM.nextFloat() - 0.5f) * 0.01)));
  	newCoords.setY((float) (_coordinates[1] + ((RANDOM.nextFloat() - 0.5f) * 0.01)));
  	return newCoords;
  }
  
}
