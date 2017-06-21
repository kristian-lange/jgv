package jgv.interfaces;

public interface ITickable
{
  
  /**
   * This method is called once in every graphic's draw cycle.
   * @param frameTime The approximate time length in seconds between two frames.
   */
  void tick(float frameTime);
  
}
