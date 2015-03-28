package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YouWin implements Screen {
	BitmapFont font;
	SpriteBatch batch;
	int vpWidth;
	int vpHeight;
	Game game;
	public YouWin (int viewWidth,int viewHeight,Game game){
		batch = new SpriteBatch();
		font = new BitmapFont();
		vpWidth = viewWidth;
		vpHeight = viewHeight;
		font.scale(2f);
		this.game = game;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//render a text field saying game over
		//if tapped game restarts at main menu
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "YOU WIN",vpWidth/2-100, vpHeight/2);
		batch.end();
		if(Gdx.input.isTouched()&&Gdx.input.justTouched()){
			game.setScreen(new MainMenu(game));
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
