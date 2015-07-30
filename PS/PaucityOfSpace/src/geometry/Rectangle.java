package geometry;

import math.CommonMath;
import math.Vector;

/**
 * TopLeft and bottomRight have to be applied to centre vector before acquiring actual points.
 */
public class Rectangle extends Shape
{
    /***/
    private Vector topLeft;
    /***/
    private Vector bottomRight;
    
    /**
     * @param centre
     * @param topLeft
     */
    public Rectangle(final Vector centre, final Vector topLeft)
    {
        this(centre, topLeft, topLeft.rotateNew(Math.toRadians(180)));
    }
    
    /**
     * @param centre
     * @param topLeft
     * @param bottomRight
     */
    public Rectangle(final Vector centre, final Vector topLeft, final Vector bottomRight)
    {
        super(centre);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    
    /**
     * @return X axis minimum
     */
    public double left()
    {
        return Math.min(topLeft.getX() + getCentre().getX(), bottomRight.getX() + getCentre().getX());
    }

    /**
     * @return X axis maximum
     */
    public double right()
    {
        return Math.max(topLeft.getX() + getCentre().getX(), bottomRight.getX() + getCentre().getX());
    }

    /**
     * @return Y axis minimum
     */
    public double top()
    {
        return Math.min(topLeft.getY() + getCentre().getY(), bottomRight.getY() + getCentre().getY());
    }

    /**
     * @return Y axis maximum
     */
    public double bottom()
    {
        return Math.max(topLeft.getY() + getCentre().getY(), bottomRight.getY() + getCentre().getY());
    }
    
    /**
     * @return new
     */
    public Vector topLeft()
    {
        return new Vector(left(), top());
    }
    
    /**
     * @return new
     */
    public Vector topRight()
    {
        return new Vector(right(), top());
    }
    
    /**
     * @return new
     */
    public Vector bottomRight()
    {
        return new Vector(right(), bottom());
    }
    
    /**
     * @return new
     */
    public Vector bottomLeft()
    {
        return new Vector(left(), bottom());
    }
    
    /**
     * @return squared length of the top/bottom side
     */
    public double sideLength()
    {
        return Math.sqrt(CommonMath.squaredDistance(topLeft(), bottomRight()));
    }
    
    /**
     * @return squared length of the left/right side
     */
    public double topLength()
    {
        return Math.sqrt(CommonMath.squaredDistance(topLeft(), topRight()));
    }
    
    /**
     * @return
     */
    public Vector topLeftVector()
    {
        return topLeft;
    }
    
    /**
     * @return
     */
    public Vector bottomRightVector()
    {
        return bottomRight;
    }
}
