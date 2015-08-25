package milech.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import milech.objects.Bullet;
import milech.window.Handler;

public class KeyInput extends KeyAdapter {
	
	Handler handler;
	private int lastKeySidesMovePressed;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public boolean ifLastSidesMoveLeft() {
		return lastKeySidesMovePressed == KeyEvent.VK_LEFT;
	}
	public boolean ifLastSidesMoveRight() {
		return lastKeySidesMovePressed == KeyEvent.VK_RIGHT;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player) {
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setMovingRight(true);
					tempObject.setLastSideMoveRight();
				}
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setMovingLeft(true);
					tempObject.setLastSideMoveLeft();
				}
				if(key == KeyEvent.VK_SPACE/* && !tempObject.isJumping()*/) {
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				if(key == KeyEvent.VK_W) {
					Bullet newBullet = new Bullet(tempObject.getX() + 20, tempObject.getY() + 30, 
							ObjectId.Bullet, tempObject.getFacing() * 8);
					if(tempObject.getFacing() == 1) {
						newBullet.setLastSideMoveRight();
					}
					else {
						newBullet.setLastSideMoveLeft();
					}
					handler.addObject(newBullet);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ObjectId.Player) {
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setMovingRight(false);
				}
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setMovingLeft(false);
				}
			}
		}
	}

}
