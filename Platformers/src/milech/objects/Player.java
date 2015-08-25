package milech.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import milech.framework.GameObject;
import milech.framework.ObjectId;
import milech.framework.Texture;
import milech.window.Animation;
import milech.window.Game;
import milech.window.Handler;

public class Player extends GameObject{
	
	private float width = 48, height = 96;
	private float gravity = 0.5f; 
	private final float MAX_SPEED = 10;
	
	Handler handler;
	Texture texture = Game.getTexture();
	private Animation playerWalk;
	private Animation playerJump;
	
	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		playerWalk = new Animation(5, texture.playerWalk);
		playerJump = new Animation(10, texture.playerJump);
	}
	
	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		if(falling || jumping) {
			velY += gravity;
			if(velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		if(movingRight == true && movingLeft == false) {
			velX = 5;
		}
		if(movingLeft == true && movingRight == false) {
			velX = -5;
		}
		if(movingLeft && movingRight) {
			velX = 0;
		}
		if(movingRight == false && movingLeft == false) {
			velX = 0;
		}
		
		collision(object);
		
		playerWalk.runAnimation();
		playerJump.runAnimation();
		
	}
	
	private void collision(LinkedList<GameObject> object) {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject =  handler.object.get(i);
			if(tempObject.getId() == ObjectId.Block) {
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					//TODO [milech] Make it not hard coded
					y = tempObject.getY() + 32;
					velY = 0; 
				}
				
				if(getBoundsDown().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0; 
					falling = false;
					jumping = false; 
				}
				else {
					falling = true;
				}

				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width - 2;
				}
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
				}
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
//		g.setColor(Color.red);
		if(jumping) { // jump
			if(lastSideMoveRight) {
				playerJump.drawAnimation(g, (int)x, (int)y, 48, 96);
			}
			else if(lastSideMoveLeft) {
				playerJump.drawAnimation(g, (int)(x + width), (int)y, -48, 96);
			}
		}
		else {
			if(velX == 0){ // stand still
				if(lastSideMoveRight)
					g.drawImage(texture.playerStand[0], (int)x, (int)y, 48, 96, null); // face right
				else if(lastSideMoveLeft) {
					g.drawImage(texture.playerStand[0], (int)(x + width), (int)y, -48, 96, null); // face left
				}
			}
			else if(velX > 0) { // walk right
				playerWalk.drawAnimation(g, (int)x, (int)y, 48, 96);
			}
			else if(velX < 0) { // walk left
				playerWalk.drawAnimation(g, (int)(x + width), (int)y, -48, 96);
			}
		}
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g2d.draw(getBoundsDown());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());
	}

	public Rectangle getBoundsDown() {
		return new Rectangle((int) ((int)x + (width/2 - ((width/2)/2))), (int)y + (int)(height / 2), (int)width / 2, (int)height / 2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2) - ((width/2)/2)), (int)y, (int)width / 2, (int)height / 2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height - 10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

}
