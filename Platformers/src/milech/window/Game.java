package milech.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import milech.framework.KeyInput;
import milech.framework.ObjectId;
import milech.framework.Texture;
import milech.objects.Block;
import milech.objects.Player;

public class Game extends Canvas implements Runnable {
	 
	private static final long serialVersionUID = -1428139567289818055L;
	private boolean running = false;
	private Thread thread; 
	public static int WIDTH, HEIGHT;
	private BufferedImage level = null;
	
	//Objects
	private Handler handler;
	private static Texture texture;
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		 
		texture = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/levels/level1.png"); // loading level
		
		handler = new Handler(); 

		loadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	}
	public synchronized void start() {
		
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		System.out.println("Thread has begun");
		init();
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {
		 handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// Draw here
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		handler.render(g);
		//////
		g.dispose();
		bs.show();
	}
	
	private void loadImageLevel(BufferedImage image) {
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
					handler.addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
				}
				if(red == 128 && green == 128 & blue == 128) {
					handler.addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
				}
				if(red == 0 && green == 0 & blue == 255) {
					handler.addObject(new Player(xx * 32, yy * 32, handler, ObjectId.Player));
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Window(800, 600, "Platformers", new Game());
	}
	
	public static Texture getTexture() {
		return texture;
	}

}
