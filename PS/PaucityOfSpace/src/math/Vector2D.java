package math;

/**
 * [x, y]
 */
public class Vector2D
{
    /***/
    private double x;
    /***/
    private double y;
    
    /**
     * @param x
     * @param y
     */
    public Vector2D(final double x, final double y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @param scalar
     */
    public void multiply(final double scalar)
    {
        x *= scalar;
        y *= scalar;
    }
    
    /**
     * @param v
     */
    public void add(final Vector2D v)
    {
        this.x += v.x;
        this.y += v.y;
    }
    
    /**
     * @param scalar
     */
    public void divide(final double scalar)
    {
        x /= scalar;
        y /= scalar;
    }
    
    /**
     * @param scalar
     * @return copy of this vector, divided by the scalar
     */
    public Vector2D divideNew(final double scalar)
    {
        Vector2D n = new Vector2D(x, y);
        n.divide(scalar);
        return n;
    }
    
    /**
     * @return
     */
    public double getX()
    {
        return x;
    }
    
    /**
     * @return
     */
    public double getY()
    {
        return y;
    }
}
