package milech.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import milech.framework.GameObject;
import milech.framework.ObjectId;

public class TestObject extends GameObject {
	public TestObject(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 32);
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getVelX() {
		return this.velX;
	}

	@Override
	public float getVelY() {
		return this.velY;
	}

	@Override
	public void setVelX(float velX) {
		this.velX = velX;
	}

	@Override
	public void setVelY(float velY) {
		this.velY = velY;
	}

	@Override
	public ObjectId getId() {
		return this.id;
	}
	
}
