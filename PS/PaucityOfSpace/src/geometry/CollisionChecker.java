package geometry;

/**
 * Can tell if there is a collision between Shapes.
 */
public interface CollisionChecker
{
    
    /**
     * Will check if there is a collision between given Shapes.
     * 
     * @param a
     * @param b
     * @return true if there is a collision
     */
    public boolean check(final Shape a, final Shape b);
    
}
