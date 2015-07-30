package demos;

import javax.swing.JFrame;

import physics.PhysicsEngine;

/**
 * Ball will fall down and bounce.
 */
public class Ball
{
    
    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        View frame = new View("Bouncing Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 768);
        frame.show();
        frame.loop();
    }
    
}
