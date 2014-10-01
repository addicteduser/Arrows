package arrows;

import java.awt.Graphics2D;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameObject;
import java.awt.event.KeyEvent;

public class GameOver extends GameObject {

    private String BG = "./resources/img/gameover.png";
    private Block gameover;
	
	public GameOver(GameEngine parent) {
		super(parent);
	}

	@Override
	public void initResources() {
		gameover = new Block(getImage(BG), 0, 0);

	}

	@Override
	public void render(Graphics2D arg0) {
		gameover.render(arg0);

	}

	@Override
	public void update(long arg0) {
		if (keyPressed(KeyEvent.VK_ENTER)) {
                    parent.nextGameID = 0;
                    finish(); 
                    
                }
        }

}
