package milech.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import milech.framework.GameObject;
import milech.framework.ObjectId;
import milech.objects.Block;
import milech.objects.Flag;
import milech.objects.Player;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	private Camera camera;
	
	private BufferedImage level1 = null, level2 = null;
	
	public Handler(Camera camera) {
		this.camera = camera;
		BufferedImageLoader loader = new BufferedImageLoader();
		level1 = loader.loadImage("/levels/level1.png"); // loading level
		level2 = loader.loadImage("/levels/level2.png"); // loading level
	}
	
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
	
	private void clearLevel() {
		object.clear();
	}
	
	public void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight(); 
		
		System.out.println("width, height: " + w + " "+ h);
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) {
					addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
				}
				if(red == 128 && green == 128 & blue == 128) {
					addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
				}
				if(red == 0 && green == 0 & blue == 255) {
					addObject(new Player(xx * 32, yy * 32, this, camera, ObjectId.Player));
				}
				if(red == 255 && green == 255 & blue == 0) {
					addObject(new Flag(xx * 32, yy * 32, ObjectId.Flag));
				}
			}
		}
	}
	
	public void switchLevel() {
		clearLevel();
		camera.setX(0);
		switch(Game.LEVEL) {
		case 1:
			loadImageLevel(level1);
			break;
		case 2:
			loadImageLevel(level2);
			break;
		}
		Game.LEVEL++;
	}
}
