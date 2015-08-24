package milech.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import milech.framework.GameObject;
import milech.framework.ObjectId;
import milech.framework.Texture;
import milech.window.Game;

public class Block extends GameObject {
	
	private int type;
	Texture texture = Game.getTexture();
	
	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}
	

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics g) {
		if(type == 0) { // rock block
			g.drawImage(texture.block[0], (int)x, (int)y, null);
		}
		if(type == 1) { // grass block
			g.drawImage(texture.block[1], (int)x, (int)y, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
