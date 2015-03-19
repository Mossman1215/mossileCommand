package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Game;


public class Main extends Game {
	int currentScore;
	int currentWave;
	@Override
	public void create() {
		this.setScreen(new SinglePlayer(this,0,1));
	}
	
}
