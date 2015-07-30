package math;

/**
 * Basic functions.
 */
public class CommonMath
{
    /**
     * Squared minimum distance between the ab line and the p point.
     * Will not modify arguments.
     * 
     * @param a beginning of the line
     * @param b end of the line
     * @param p point
     * @return squared minumum distance between ab and p
     */
    public static double minimum_distance(final Vector a, final Vector b, final Vector p)
    {
        final double abDistanceSquared = squaredDistance(a, b);
        
        if(abDistanceSquared == 0d)
        {
            return squaredDistance(a, p);
        }
        
        final double projectedDist = Vector.dotProduct(p.subtractNew(a), b.subtractNew(a)) / abDistanceSquared;
        if(projectedDist < 0d)
        {
            return CommonMath.squaredDistance(p, a);
        }
        if(projectedDist > 1d)
        {
            return CommonMath.squaredDistance(p, b);
        }
        
        final Vector projection = b.subtractNew(a);
        projection.multiply(projectedDist);
        projection.add(a);
        
        return CommonMath.squaredDistance(p, projection);
    }
    
    /**
     * Will not modify arguments.
     * 
     * @param a
     * @param b
     * @return (a.x-b.x)^2 + (a.y - b.y)^2
     */
    public static double squaredDistance(final Vector a, final Vector b)
    {
        double x = a.getX() - b.getX();
        double y = a.getY() - b.getY();
        return x*x + y*y;
    }
}
