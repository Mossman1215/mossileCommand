package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainMenu implements Screen {
	final Game game;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	SpriteBatch batch = new SpriteBatch();
	Rectangle menu1 = new Rectangle(-90, 40, 180, 60);
	Rectangle menu2 = new Rectangle(-90,-40,180,60);
	BitmapFont font = new BitmapFont();
	Vector3 touchpt = new Vector3();
	
	public MainMenu(final Game game){
		this.game = game;
		camera = new OrthographicCamera(480, 320);
		shapeRenderer = new ShapeRenderer();
		font.scale(2);
	}
	@Override
	public void render(float delta) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.rect(menu1.x,menu1.y,menu1.width,menu1.height);
		shapeRenderer.rect(menu2.x, menu2.y, menu2.width, menu2.height);
		shapeRenderer.end();
		batch.begin();
		font.draw(batch, "Mossile Command", menu1.x+210, menu1.y+270);
		batch.end();
		if(Gdx.input.isTouched()){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			touchpt.x = x;
			touchpt.y = y;
			touchpt.z = 0;
			camera.unproject(touchpt);
			if(menu1.contains(touchpt.x,touchpt.y)){
				load1P();
			}
			if(menu2.contains(touchpt.x,touchpt.y)){
				load2P();
			}
		}
	}
	public void load1P(){
		game.setScreen(new SinglePlayer(game));
		this.dispose();
	}
	public void load2P(){
		//game.setScreen(new TwoPlayerScreen(game));
		this.dispose();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
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
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
