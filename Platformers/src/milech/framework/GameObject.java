package milech.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean movingRight = false;
	protected boolean movingLeft = false;
	protected boolean lastSideMoveRight = true;
	protected boolean lastSideMoveLeft = false;
	
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract Rectangle getBounds();

	public ObjectId getId() {
		return id;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean isFalling() {
		return falling;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	public abstract void render(Graphics g);
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public abstract void tick(LinkedList<GameObject> object);

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setLastSideMoveRight() {
		this.lastSideMoveRight = true;
		this.lastSideMoveLeft = false;
	}
	
	public void setLastSideMoveLeft() {
		this.lastSideMoveRight = false;
		this.lastSideMoveLeft = true;
	}
	
	public int getFacing() {
		if(lastSideMoveRight == true) {
			return 1;
		}
		return -1;
	}
	
}
 