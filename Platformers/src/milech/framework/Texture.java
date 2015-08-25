package milech.framework;

import java.awt.image.BufferedImage;

import milech.window.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps; // block, player
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	public BufferedImage[] block = new BufferedImage[2]; // floor types
	public BufferedImage[] playerStand = new BufferedImage[1]; // player stands
	public BufferedImage[] playerWalk = new BufferedImage[7]; // player walks
	public BufferedImage[] playerJump = new BufferedImage[3]; // player jumps

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

		playerStand[0] = ps.getImage(1, 1, 32, 64);
		
		playerWalk[0] = ps.getImage(1, 1, 32, 64); // frame for player walk
		playerWalk[1] = ps.getImage(2, 1, 32, 64); // frame for player walk
		playerWalk[2] = ps.getImage(3, 1, 32, 64); // frame for player walk
		playerWalk[3] = ps.getImage(4, 1, 32, 64); // frame for player walk
		playerWalk[4] = ps.getImage(5, 1, 32, 64); // frame for player walk
		playerWalk[5] = ps.getImage(6, 1, 32, 64); // frame for player walk
		playerWalk[6] = ps.getImage(7, 1, 32, 64); // frame for player walk
		
		playerJump[0] = ps.getImage(8, 2, 32, 64); // frame for player jump
		playerJump[1] = ps.getImage(9, 2, 32, 64); // frame for player jump
		playerJump[2] = ps.getImage(10, 2, 32, 64); // frame for player jump
	}

}
