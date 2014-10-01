package arrows;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;

public class GameFrame extends GameObject {
	
	private String BG = "./resources/img/bg.png";
	private String BUFFER = "./resources/img/buffer.png";
	private String HIT = "./resources/img/hit.png";
	
	private String UP = "./resources/img/up.png";
	private String DOWN = "./resources/img/down.png";
	private String LEFT = "./resources/img/left.png";
	private String RIGHT = "./resources/img/right.png";
	
	private String UP2 = "./resources/img/up2.png";
	private String DOWN2 = "./resources/img/down2.png";
	private String LEFT2 = "./resources/img/left2.png";
	private String RIGHT2 = "./resources/img/right2.png";
	
	private int dimension = 10;
	
	private Block background;
	private Block hit;
	private Block buffer;
	
	private Block[] arrows = new Block[7];
	private int[] arrowsID = new int [7];
	
	private SpriteGroup ARROWS;
	
	private int pressed = 0;
	private int counter = 0; //increments when you typed correctly
	
	private int score = 0;
	
	private int startX = 22; //starting position of buffer
	private int endX = 582; //end position of buffer
	private int lowerRange = 481; //lower range of score area
	private int upperRange = 540; //upper range of score area
	
	boolean created = false;
	
	private int speed;
	private Timer delay;
	
	private int secCtr;
	private Timer secTimer;

	public GameFrame(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		background = new Block(getImage(BG), 0, 0);
		hit = new Block(getImage(HIT), 481, 352);
		buffer = new Block(getImage(BUFFER), startX, 352);
		
		speed = Arrows.speed;
		delay = new Timer(speed);
		
		secCtr = 60;
		secTimer = new Timer(1000);
		
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
		
		if (secTimer.action(l))
			displayTime();
		
		if (delay.action(l))
			moveBuffer();
		
		readInput();
		
		background.update(l);
		hit.update(l);
		buffer.update(l);
		ARROWS.update(l);
	}
	
	public void displayTime() {
		System.out.println(secCtr);
		secCtr--;
	}
	
	//creating a random set of arrows
	public void createSet() { 
		int max = 4;
		int min = 1;
		int range = max - min + 1;
		int rand;
		int x = 26;
		
		counter = 0;
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
		if (keyPressed(KeyEvent.VK_UP)) {
			if (counter == 7) {
				counter = 0;
				resetArrows();
			}
			else {
				pressed = 1;
				checkInput();
			}
			
		}
		else if (keyPressed(KeyEvent.VK_RIGHT)) {
			if (counter == 7) {
				counter = 0;
				resetArrows();
			}
			else {
				pressed = 2;
				checkInput();
			}
		}
		else if (keyPressed(KeyEvent.VK_DOWN)) {
			if (counter == 7) {
				counter = 0;
				resetArrows();
			}
			else {
				pressed = 3;
				checkInput();
			}
		}
		else if (keyPressed(KeyEvent.VK_LEFT)) {
			if (counter == 7) {
				counter = 0;
				resetArrows();
			}
			else {
				pressed = 4;
				checkInput();
			}
		}
		else if (keyPressed(KeyEvent.VK_SPACE)) {
			if (counter == 7 && lowerRange < buffer.getX() && buffer.getX() < upperRange) {				
				getScore();				
				createSet();
				System.out.println(score);
			}
			else {
				createSet();
			}
		}
		
	}
	
	public void checkInput() {
		switch(pressed) {
			case 1: 
				if (arrowsID[counter] == 1) {
					arrows[counter].setImage(getImage(UP2));
					counter++;
				}
				else {
					counter = 0;
					resetArrows();
				}
				break;
			case 2:
				if (arrowsID[counter] == 2) {
					arrows[counter].setImage(getImage(RIGHT2));
					counter++;
				}
				else {
					counter = 0;
					resetArrows();
				}
				break;
			case 3:
				if (arrowsID[counter] == 3) {
					arrows[counter].setImage(getImage(DOWN2));
					counter++;
				}
				else {
					counter = 0;
					resetArrows();
				}
				break;
			case 4:
				if (arrowsID[counter] == 4) {
					arrows[counter].setImage(getImage(LEFT2));
					counter++;
				}
				else {
					counter = 0;
					resetArrows();
				}
				break;
		}
	}
	
	public void resetArrows() {
		for (int i = 0; i < 7; i++) {
			arrows[i].setImage(getImage(assignArrows(arrowsID[i])));
		}
	}
	
	public void moveBuffer() {
		if (buffer.getX() < endX) {
			buffer.setX(buffer.getX() + dimension);
		}
		else {
			buffer.setX(startX);
			created = false;
		}
		
		if (buffer.getX() > upperRange && created == false) {
			created = true;
			createSet();
		}
	}

	public void getScore() {
		score += 100;
	}
}
