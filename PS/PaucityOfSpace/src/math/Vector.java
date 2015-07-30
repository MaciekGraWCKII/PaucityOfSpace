package math;


/**
 * [x, y]
 */
public class Vector
{
    /**[0,0] shan't do no harm*/
    private static Vector benignVector = new Vector(0d, 0d);
    /***/
    private double x;
    /***/
    private double y;
    
    public Vector(final Vector v)
    {
        this.x = v.getX();
        this.y = v.getY();
    }
    
    /**
     * @param x
     * @param y
     */
    public Vector(final double x, final double y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @param a
     * @param b
     * @return
     */
    public static double dotProduct(final Vector a, final Vector b)
    {
        return a.x * b.x + a.y * b.y;
    }
    
    /**
     * Applying this vector should not cause any change whatsoever.
     * 
     * @return
     */
    public static Vector getBenign()
    {
        return benignVector;
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
    public void add(final Vector v)
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
    public Vector divideNew(final double scalar)
    {
        Vector n = new Vector(x, y);
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

    /**
     * @param a
     */
    public void subtract(final Vector v)
    {
        this.x -= v.x;
        this.y -= v.y;
    }
    
    /**
     * @param v
     * @return new Vector, result of subtraction
     */
    public Vector subtractNew(final Vector v)
    {
        return new Vector(x - v.x, y - v.y);
    }
    
    public double length()
    {
        return Math.sqrt(x * x + y * y);
    }
    
    /**
     * @param radians
     */
    public void rotate(final double radians)
    {
        final double nx = x * Math.cos(radians) - y * Math.sin(radians);
        final double ny = y * Math.sin(radians) + y * Math.cos(radians);
        x = nx;
        y = ny;
    }

    /**
     * @param radians
     * @return
     */
    public Vector rotateNew(double radians)
    {
        Vector nv = new Vector(this);
        nv.rotate(radians);
        return nv;
    }
}
