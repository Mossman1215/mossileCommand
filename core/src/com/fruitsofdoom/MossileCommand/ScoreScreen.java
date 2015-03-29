package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	OrthographicCamera camera;
	public ScoreScreen (int viewWidth,int viewHeight,Game game,int roundScore,int previousScore,int wave,OrthographicCamera camera){
		batch = new SpriteBatch();
		font = new BitmapFont();
		vpWidth = viewWidth;
		vpHeight= viewHeight;
		font.scale(2f);
		this.game = game;
		this.roundScore = roundScore;
		this.previousScore = previousScore;
		this.wave = wave;
		this.camera = camera;
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
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		font.draw(batch, "Your score",-100, 0);
		font.draw(batch, "round score: "+roundScore,-100, -50);
		font.draw(batch, "previous score +"+previousScore,-100, -100);
		int totalScore = previousScore+roundScore;
		font.draw(batch, "totalScore"+totalScore,-100, -150);
		font.draw(batch, "highscore: "+highscore , -10, 100-360);
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
