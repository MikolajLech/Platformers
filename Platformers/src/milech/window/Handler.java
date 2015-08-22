package milech.window;

import java.awt.Graphics;
import java.util.LinkedList;

import milech.framework.GameObject;
import milech.framework.ObjectId;
import milech.objects.Block;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void remove(GameObject object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		for(int xx = 0; xx < Game.WIDTH + 32; xx += 32) {
			addObject(new Block(xx, Game.HEIGHT - 32, ObjectId.Block));
		}
		for(int yl = 0; yl < Game.HEIGHT + 32; yl += 32) {
			addObject(new Block(Game.WIDTH - 32, yl, ObjectId.Block));
		}
		for(int yl = 0; yl < Game.HEIGHT + 32; yl += 32) {
			addObject(new Block(0, yl, ObjectId.Block));
		}
	}
}
