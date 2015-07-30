package physics;

import java.util.List;

/**
 * Controls the physical world.
 */
public class PhysicsEngine
{
    /**Earths gravitational constant*/
    public static double GRAVITATIONAL_CONSTANT = 9.81;
    public static int NANOSECONDS_IN_SECOND = 1000000000;
    /***/
    private List<PhysicalBody> objects;
    /***/
    private Vector forceOfGravity;
    /**Time of the last physics frame, in nanoseconds.*/
    private long lastFrameTime = 0;
    /**Time of the current physics frame, in nanoseconds.*/
    private long currentFrameTime;
    /**Time difference between the last and current frame, in seconds.*/
    private double timeDelta;
    
    public PhysicsEngine()
    {
        forceOfGravity = new Vector(0, -1);
    }
    
    /**
     * Calculates one physical frame.
     */
    public void doFrame()
    {
        currentFrameTime = System.nanoTime();
        timeDelta = ((double)(currentFrameTime - lastFrameTime)) / NANOSECONDS_IN_SECOND;
        applyForces();
        executeMovement();
        collide();
        lastFrameTime = currentFrameTime;
    }
    
    /**
     * @return original list with original bodies
     */
    public List<PhysicalBody> getPhysicalBodies()
    {
        return objects;
    }
    
    /**
     * 
     */
    private void applyForces()
    {
        for(PhysicalBody body : objects)
        {
            body.clearForces();
            body.applyForce(forceOfGravity);
        }
    }
    
    /**
     * 
     */
    private void executeMovement()
    {
        for(PhysicalBody body : objects)
        {
            body.move(timeDelta);
        }
    }
    
    /**
     * 
     */
    private void collide()
    {
        
    }
}
