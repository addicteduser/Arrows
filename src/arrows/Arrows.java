package arrows;

import java.awt.Dimension;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.GameObject;

public class Arrows extends GameEngine {

	@Override
	public GameObject getGame(int gameID) {
		switch (gameID) {
			case 0: return new IntroMenu(this);
			case 1: return new GameFrame1(this);
			case 2: return new GameFrame2(this);
			case 3: return new GameFrame3(this);
			case 4: return new GameOver(this);
		}
		return null;
	}
	
	public static void main(String[] args) {
		GameLoader game = new GameLoader();
		game.setup(new Arrows(), new Dimension(640, 640), false);
		game.start();

	}

}
