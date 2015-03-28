package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreScreen implements Screen {
	
	BitmapFont font;
	SpriteBatch batch;
	int vpWidth;
	int vpHeight;
	Game game;
	int roundScore;
	int previousScore;
	int wave;
	String highscore;
	public ScoreScreen (int viewWidth,int viewHeight,Game game,int roundScore,int previousScore,int wave){
		batch = new SpriteBatch();
		font = new BitmapFont();
		vpWidth = viewWidth;
		vpHeight = viewHeight;
		font.scale(2f);
		this.game = game;
		this.roundScore = roundScore;
		this.previousScore = previousScore;
		this.wave = wave;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		int total = roundScore+previousScore;
		if(!Gdx.files.local("highscore.txt").exists()){
			FileHandle file = Gdx.files.local("highscore.txt");
			file.writeString(""+total, false);
		}else{
			FileHandle file = Gdx.files.local("highscore.txt");
			String highscore = file.readString();
			if(Integer.parseInt(highscore)<total){
				file.writeString(""+total, false);
			}
			this.highscore =highscore;
		}
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//render a text field saying game over
		//if tapped game restarts at main menu
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "Your score",vpWidth/2-100, vpHeight/2);
		font.draw(batch, "round score: "+roundScore,vpWidth/2-100, vpHeight/2-50);
		font.draw(batch, "previous score +"+previousScore,vpWidth/2-100, vpHeight/2-100);
		int totalScore = previousScore+roundScore;
		font.draw(batch, "totalScore"+totalScore,vpWidth/2-100, vpHeight/2-150);
		font.draw(batch, "highscore: "+highscore , vpWidth/2-10, 100);
		batch.end();
		if(Gdx.input.isTouched()&&Gdx.input.justTouched()){
			game.setScreen(new SinglePlayer(game,totalScore,wave));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		font.dispose();
	}

}
