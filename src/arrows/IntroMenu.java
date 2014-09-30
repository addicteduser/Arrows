package arrows;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;

public class IntroMenu extends GameObject {
	
	private String HEADER = "./resources/img/header.png";
	private String LEVEL1 = "./resources/img/level1.png";
	private String LEVEL2 = "./resources/img/level2.png";
	private String LEVEL3 = "./resources/img/level3.png";
	
	private Block background;
	private Block level;
	
	private int levelPressed = 1;
	private boolean play = false;
	
	public IntroMenu(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		background = new Block(getImage(HEADER), 0, 0);
		level = new Block(getImage(LEVEL1), 0, 0);
	}

	@Override
	public void render(Graphics2D gd) {
		background.render(gd);
		level.render(gd);
	}

	@Override
	public void update(long l) {
		background.update(l);
		ReadInput();
		changeImage();
		selection();
	}
	
	public void ReadInput() {
		if (keyPressed(KeyEvent.VK_UP)) {
			play = false;
			switch(levelPressed) {
				case 1: levelPressed = 1;
					break;
				case 2: levelPressed = 1;
					break;
				case 3: levelPressed = 2;
					break;
			}
			
		} else if (keyPressed(KeyEvent.VK_DOWN)) {
			play = false;
			switch(levelPressed) {
				case 1: levelPressed = 2;
					break;
				case 2: levelPressed = 3;
					break;
				case 3: levelPressed = 3;
					break;
			}
			
		} else if (keyPressed(KeyEvent.VK_ENTER)) {
			play = true;
		}
	}
	
	public void changeImage() {
		switch(levelPressed) {
			case 1: level.setImage(getImage(LEVEL1));
				break;
			case 2: level.setImage(getImage(LEVEL2));
				break;
			case 3: level.setImage(getImage(LEVEL3));
				break;
		}
	}
	
	public void selection() {
		if (play == true) {
			switch(levelPressed) {
				case 1: Arrows.speed = 60;
					parent.nextGameID = 1;
					break;
				case 2: Arrows.speed = 50;
					parent.nextGameID = 1;
					break;
				case 3: Arrows.speed = 40;
					parent.nextGameID = 1;
					break;
			}			
			finish();
		}
	}

}
