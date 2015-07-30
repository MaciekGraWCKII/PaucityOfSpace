package geometry;

import math.CommonMath;
import math.Vector;

public class SimpleCollisionChecker implements CollisionChecker
{
    private CollisionHelper rectangleHelper = new RectangleHelper();
    private CollisionHelper circleRectangleHelper = new CircleRectangleHelper();
    private CollisionHelper circleHelper = new CircleHelper();
    
    /* (non-Javadoc)
     * @see geometry.CollisionChecker#check(geometry.Shape, geometry.Shape)
     */
    @Override
    public boolean check(final Shape a, final Shape b)
    {
        if(a instanceof Rectangle)
        {
            if(b instanceof Circle)
            {
                return circleRectangleHelper.check(b, a);
            }
            else if(b instanceof Rectangle)
            {
                return rectangleHelper.check(a, b);
            }
        }
        else if(a instanceof Circle)
        {
            if(b instanceof Circle)
            {
                return circleHelper.check(a, b);
            }
            else if(b instanceof Rectangle)
            {
                return circleRectangleHelper.check(a, b);
            }
        }
        return false;
    }
    
    /**
     *
     */
    private interface CollisionHelper
    {
        /**
         * @param a
         * @param b
         * @return
         */
        public boolean check(final Shape a, final Shape b);
    }
    
    /**
     *
     */
    private class RectangleHelper implements CollisionHelper
    {

        /* (non-Javadoc)
         * @see geometry.SimpleCollisionChecker.CollisionHelper#check(geometry.Shape, geometry.Shape)
         */
        @Override
        public boolean check(final Shape a, final Shape b)
        {
            Rectangle r1 = (Rectangle) a;
            Rectangle r2 = (Rectangle) b;
            
            return(r1.left() <= r2.right() &&
                    r2.left() <= r1.right() &&
                    r1.top() <= r2.bottom() &&
                    r2.top() <= r1.bottom());
        }
    }
    
    /**
     *
     */
    private class CircleHelper implements CollisionHelper
    {

        @Override
        public boolean check(final Shape a, final Shape b)
        {
            Circle x = (Circle) a;
            Circle y = (Circle) b;
            
            return (x.getRadius() + y.getRadius()) < CommonMath.squaredDistance(x.getCentre(), y.getCentre());
        }
        
    }
    
    private class CircleRectangleHelper implements CollisionHelper
    {
        @Override
        public boolean check(final Shape a, final Shape b)
        {
            final Circle c = (Circle) a;
            final Rectangle r = (Rectangle) b;
            final double distTop = CommonMath.minimum_distance(r.topLeft(), r.topRight(), c.getCentre());
            final double distLeft = CommonMath.minimum_distance(r.bottomLeft(), r.topLeft(), c.getCentre());
            final double distBottom = CommonMath.minimum_distance(r.bottomLeft(), r.bottomRight(), c.getCentre());
            final double distRight = CommonMath.minimum_distance(r.topRight(), r.bottomRight(), c.getCentre());
            
            //Circle centre inside rectangle
            if(distTop + distBottom == r.sideLength() && distLeft + distRight == r.topLength())
            {
                return true;
            }
            //Circle centre outside rectangle, but close enough
            if(distTop <= c.getRadius())
            {
                return true;
            }
            if(distLeft <= c.getRadius())
            {
                return true;
            }
            if(distBottom <= c.getRadius())
            {
                return true;
            }
            if(distRight <= c.getRadius())
            {
                return true;
            }
            
            return false;
        }
    }
    
}
