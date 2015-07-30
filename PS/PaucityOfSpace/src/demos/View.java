package demos;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import geometry.Circle;
import geometry.Rectangle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import math.Vector;
import physics.PhysicalBody;
import physics.PhysicsEngine;

public class View extends JFrame implements Runnable, ActionListener
{
    /***/
    private static final long serialVersionUID = 1L;
    private final PhysicsEngine physics;
    private int frames;
    private Timer timer = new Timer(1000, this);
    private boolean pause = false;
    private PhysicalBody floor;
    private PhysicalBody ball;
    
    public View(final String name)
    {
        super(name);
        
        addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e){pause = !pause;}

            @Override
            public void keyReleased(KeyEvent arg0)
            {
                physics.doFrame();
                repaint();
                
            }

            @Override
            public void keyTyped(KeyEvent arg0)
            {
                // TODO Auto-generated method stub
                
            }
        });
        
        physics = new PhysicsEngine();
        floor = new PhysicalBody(new Rectangle(new Vector(512, 764), new Vector(-512, -40)));
        floor.immovable(true);
        physics.addBody(floor);
        ball = new PhysicalBody(new Circle(new Vector(512, 0), 25));
        ball.setMass(10);
        physics.addBody(ball);
    }

    public void loop()
    {
        
               
       
        
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run()
    {
        loop();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        frames = 0;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Rectangle rect = (Rectangle) floor.getShape();
        Circle circle = (Circle) ball.getShape();
        int rt = (int) Math.round(rect.top());
        int rl = (int) Math.round(rect.left());
        int width = (int) Math.round(rect.topLength());
        int height = (int) Math.round(rect.sideLength());
        int radius = (int) Math.round(circle.getRadius());
        int bl = (int) Math.round(circle.getCentre().getX()) - radius;
        int bt = (int) Math.round(circle.getCentre().getY()) - radius;
        
        g.drawString(rl + " " + rt + " " + width + " " + height, 50, 50);
        g.drawString(bl + " " + bt + " " + radius, 75, 75);
        g.drawOval(bl, bt, radius, radius);
        g.drawRect(rl, rt, width, height);
    }
}
