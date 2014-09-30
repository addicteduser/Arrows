package arrows;

import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public class GameFrame extends GameObject {
	
	private String BG = "./resources/img/bg.png";
	private String UP = "./resources/img/up.png";
	private String DOWN = "./resources/img/down.png";
	private String LEFT = "./resources/img/left.png";
	private String RIGHT = "./resources/img/right.png";
	private String BUFFER = "./resources/img/buffer.png";
	private String HIT = "./resources/img/hit.png";
	
	private Block background;
	private Block hit;
	private Block buffer;
	
	private int[] arrows = new int [7];

	public GameFrame(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		background = new Block(getImage(BG), 0, 0);
		hit = new Block(getImage(HIT), 481, 352);
		buffer = new Block(getImage(BUFFER), 20, 352);	
		
		createSet();
	}

	@Override
	public void render(Graphics2D gd) {
		background.render(gd);
		hit.render(gd);
		buffer.render(gd);
	}

	@Override
	public void update(long l) {
		background.update(l);
		hit.update(l);
		buffer.update(l);
	}
	
	public void createSet() {
		int max = 4;
		int min = 1;
		int range = max - min + 1;
		int rand;
		
		for (int i = 0; i < 9; i++) {
			rand = (int) (Math.random() * range) + min;
			arrows[i] = rand;
			
		}
	}

}
