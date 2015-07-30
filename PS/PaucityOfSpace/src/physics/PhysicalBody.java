package physics;

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
    private Vector velocity;
    
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
        force.multiply(getMass());
        this.force.add(force);
    }

    /**
     * Will move this body according to current force vector and specified time.
     */
    public void move(final double time)
    {
        Vector newVelocity = getVelocity(getAcceleration(), time);
        velocity.add(newVelocity);
        shape.move(velocity);
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
}
