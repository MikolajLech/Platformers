package milech.framework;

import java.awt.image.BufferedImage;

import milech.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps; // block, player
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	public BufferedImage[] block = new BufferedImage[2]; // floor types
	public BufferedImage[] player = new BufferedImage[7]; // player

	public Texture() {

		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/sheets/block_sheet.png");
			player_sheet = loader.loadImage("/sheets/player_sheet.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);

		getTextures();
	}

	private void getTextures() {
		block[0] = bs.getImage(1, 1, 32, 32); // rock block
		block[1] = bs.getImage(2, 1, 32, 32); // grass block

		player[0] = ps.getImage(1, 1, 32, 64); // frame for player
		player[1] = ps.getImage(2, 1, 32, 64); // frame for player
		player[2] = ps.getImage(3, 1, 32, 64); // frame for player
		player[3] = ps.getImage(4, 1, 32, 64); // frame for player
		player[4] = ps.getImage(5, 1, 32, 64); // frame for player
		player[5] = ps.getImage(6, 1, 32, 64); // frame for player
		player[6] = ps.getImage(7, 1, 32, 64); // frame for player
	}

}
