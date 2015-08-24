package milech.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	public BufferedImage getImage(int col, int row,  int width, int height) {
		BufferedImage image = this.image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return image;
	}
}
