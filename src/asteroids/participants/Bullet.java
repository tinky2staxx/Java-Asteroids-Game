package asteroids.participants;


import static asteroids.Constants.BULLET_DURATION;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Timer;

import asteroids.Constants;
import asteroids.Controller;
import asteroids.Participant;
import asteroids.ParticipantCountdownTimer;
import asteroids.destroyers.AlienDestroyer;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.ShipDestroyer;

public class Bullet extends Participant implements AsteroidDestroyer, AlienDestroyer
{
	private Timer time = new Timer();
	private Shape outline;
	private Controller controller;
	/*
	 * Creates the main ship's bullet
	 */
	public Bullet(Double x, Double y, Double direction, Controller controller)
	{
		super();
		super.setPosition(x, y);
		super.setVelocity(Constants.BULLET_SPEED, direction);
		this.controller = controller;
		
		Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, 0);
        poly.lineTo(0,1);
        poly.lineTo(1, 0);
        poly.closePath();  
		outline = poly;
		
		new ParticipantCountdownTimer(this, Constants.BULLET_DURATION);
		
	}
	
	
	@Override
	protected Shape getOutline() {
		return outline;
	}

	@Override
	public void collidedWith(Participant p)
	{
		if( p instanceof Asteroid)
		{
		   
		   Participant.expire(this);
		   controller.bulletKill(this);
		}
	}
	
	@Override
	public void countdownComplete (Object payload)
    {
		expire(this);
		controller.bulletKill(this);
    }
	
	
	

}
