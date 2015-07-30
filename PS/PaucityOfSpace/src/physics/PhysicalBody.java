package physics;

import math.Vector;
import geometry.Shape;

/**
 * Tries to behave like a real deal.
 */
public class PhysicalBody
{
    /***/
    private Shape shape;
    /**Vector of force currently exerted on the body.*/
    private Vector force;
    /**Continuous distribution, in kg.*/
    private double mass;
    /**Current vector of velocity.*/
    private Vector velocity = Vector.getBenign();
    /**States whether an object can be moved by external forces.*/
    private boolean immovable;
    /***/
    private Vector lastAcceleration = new Vector(0, 0);
    
    public PhysicalBody(final Shape shape)
    {
        this.shape = shape;
    }
    
    /**
     * @return original
     */
    public Shape getShape()
    {
        return shape;
    }

    /**
     * 
     */
    public void clearForces()
    {
        force = Vector.getBenign();
    }

    /**
     * Multiplies given force by the body mass and adds to the sum of forces.
     * 
     * @param force
     */
    public void applyForce(final Vector force)
    {
        if(immovable)
        {
            return;
        }
        force.multiply(getMass());
        this.force.add(force);
    }

    /**
     * Will move this body according to current force and velocity vector, and for specified time.
     *
     * @param time in seconds
     */
    public void move(final double time)
    {
        if(immovable)
        {
            return;
        }
        velocity.multiply(time);
        Vector la = new Vector(lastAcceleration);
        la.multiply(0.5);
        la.multiply(time * time);
        velocity.add(la);
        shape.move(velocity);
        
        Vector newAcceleration = getAcceleration();
        newAcceleration.add(lastAcceleration);
        newAcceleration.multiply(0.5);
        newAcceleration.multiply(time);
        velocity.add(newAcceleration);
        lastAcceleration = newAcceleration;
    }
    
    /**
     * Retrieves current velocity vector.
     * 
     * @return original
     */
    public Vector getVelocity()
    {
        return velocity;
    }
    
    /**
     * @param v
     */
    public void setVelocity(final Vector v)
    {
        this.velocity = v;
    }
    
    /**
     * @param acceleration
     * @param time
     * @return
     */
    private Vector getVelocity(Vector acceleration, double time)
    {
        acceleration.multiply(time);
        return acceleration;
    }

    /**
     * @return
     */
    private Vector getAcceleration()
    {
        return force.divideNew(getMass());
    }

    /**
     * Returns overall mass of the object, in kg.
     * Continuous mass distribution is assumed.
     * 
     * @return
     */
    public double getMass()
    {
        return mass;
    }
    
    /**
     * @return
     */
    public boolean immovable()
    {
        return immovable;
    }
    
    /**
     * @param f
     */
    public void immovable(final boolean f)
    {
        this.immovable = f;
    }

    /**
     * @param m
     */
    public void setMass(final double m)
    {
        this.mass = m;
    }
}