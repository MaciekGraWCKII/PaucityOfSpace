package geometry;

import math.Vector;
import geometry.Shape;

/**
 * 
 */
public class Circle extends Shape
{
    private double radius;
    
    /**
     * @param centre
     * @param radius
     */
    public Circle(final Vector centre, final double radius)
    {
        super(centre);
        this.radius = radius;
    }

    /**
     * @return
     */
    public double getRadius()
    {
        return radius;
    }
    
}
