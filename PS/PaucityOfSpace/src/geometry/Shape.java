package geometry;

import math.Vector;

/**
 *
 */
public class Shape
{
    private Vector centre;

    /**
     * Vector points from (0,0) to the centre of the Shape.
     * 
     * @param centre
     */
    public Shape(final Vector centre)
    {
        this.centre = centre;
    }

    /**
     * @param velocity
     */
    public void move(Vector velocity)
    {
        centre.add(velocity);
    }
    
    /**
     * Get Vector pointing from (0,0) to the centre of the Shape.
     * 
     * @return
     */
    public Vector getCentre()
    {
        return centre;
    }

}
