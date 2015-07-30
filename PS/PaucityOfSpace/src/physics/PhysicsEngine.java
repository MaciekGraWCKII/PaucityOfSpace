package physics;

import geometry.Circle;
import geometry.CollisionChecker;
import geometry.Rectangle;
import geometry.Shape;
import geometry.SimpleCollisionChecker;

import java.util.ArrayList;
import java.util.List;

import math.CommonMath;
import math.Vector;

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
    /***/
    private CollisionChecker collisions;
    
    public PhysicsEngine()
    {
        objects = new ArrayList<PhysicalBody>();
        forceOfGravity = new Vector(0, 1);
        forceOfGravity.multiply(GRAVITATIONAL_CONSTANT);
        collisions = new SimpleCollisionChecker();
        lastFrameTime = System.nanoTime();
    }
    
    /**
     * Calculates one physical frame.
     */
    public void doFrame()
    {
        currentFrameTime = System.nanoTime();
        timeDelta = ((double)(currentFrameTime - lastFrameTime)) / NANOSECONDS_IN_SECOND;
        timeDelta = 0.01;
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
    
    public void addBody(final PhysicalBody body)
    {
        objects.add(body);
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
        for(int i = 0; i < objects.size(); ++i)
        {
            for(int j = i + 1; j < objects.size(); ++j)
            {
                if(collisions.check(objects.get(i).getShape(), objects.get(j).getShape()))
                {
                    resolveCollision(objects.get(i), objects.get(j));
                }
            }
        }
    }
    
    /**
     * @param a
     * @param b
     */
    private void resolveCollision(final PhysicalBody a, final PhysicalBody b)
    {
        //If we are lucky, the velocity will move colliding objects apart in the next frame, just before checking collisions
        //If not, infinite collision glitch is always welcome
        Vector v = a.getVelocity();
        v.multiply(-1);
        a.setVelocity(v);
        v = b.getVelocity();
        v.multiply(-1);
        b.setVelocity(v);
    }
}
