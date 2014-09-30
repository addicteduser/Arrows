package arrows;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.SpriteGroup;

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
	
	private Block[] arrows = new Block[7];
	private int[] arrowsID = new int [7];
	
	private SpriteGroup ARROWS;
	
	private int pressed = 0;

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
		ARROWS.render(gd);
	}

	@Override
	public void update(long l) {
		readInput();
		
		background.update(l);
		hit.update(l);
		buffer.update(l);
		ARROWS.update(l);
	}
	
	public void createSet() {
		int max = 4;
		int min = 1;
		int range = max - min + 1;
		int rand;
		int x = 26;
		
		ARROWS = new SpriteGroup("Arrows");
		
		for (int i = 0; i < 7; i++) {
			rand = (int) (Math.random() * range) + min;
			arrowsID[i] = rand;
			arrows[i] = new Block(getImage(assignArrows(rand)), x, 242);
			x += 85;
			ARROWS.add(arrows[i]);
		}
	}
	
	public String assignArrows(int x) {
		String ARROW = "";
		switch(x) {
			case 1: ARROW = UP;
				break;
			case 2: ARROW = RIGHT;
				break;
			case 3: ARROW = DOWN;
				break;
			case 4: ARROW = LEFT;
				break;
		}
		return ARROW;
	}
	
	public void readInput() {
		if (keyPressed(KeyEvent.VK_UP))
			pressed = 1;
		else if (keyPressed(KeyEvent.VK_RIGHT))
			pressed = 2;
		else if (keyPressed(KeyEvent.VK_DOWN))
			pressed = 3;
		else if (keyPressed(KeyEvent.VK_LEFT))
			pressed = 4;
	}

}
