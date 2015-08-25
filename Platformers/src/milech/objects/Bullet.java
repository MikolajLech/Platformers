package milech.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Rotation;

import milech.framework.GameObject;
import milech.framework.ImageHandler;
import milech.framework.ObjectId;
import milech.framework.Texture;
import milech.window.Game;

public class Bullet extends GameObject{

	Texture texture = Game.getTexture();
	BufferedImage image;
	
	public Bullet(float x, float y, ObjectId id, int velX) {
		super(x, y, id);
		this.velX = velX;
		image = Scalr.rotate(ImageHandler.copyImage(texture.guns[0]), Rotation.CW_90);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 60, 60);
	}

	@Override
	public void render(Graphics g) {
		if(lastSideMoveRight) {
			g.drawImage(image, (int)x, (int)y, 60, 60, null);
		}
		else if(lastSideMoveLeft) {
			g.drawImage(image, (int)x, (int)y, -60, 60, null);
		}
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
//		ImageHandler.rotateImage(this.image);
//		image = ImageHandler.rotateImage(image);
	}


}
