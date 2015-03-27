package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	Rectangle menu1 = new Rectangle(-300, -100, 576, 180);
	Rectangle menu2 = new Rectangle(-300,-300,576,180);
	BitmapFont font = new BitmapFont();
	Vector3 touchpt = new Vector3();
	Texture title,option1,option2,helpHint,twitter;
	Sound click; 
	public MainMenu(final Game game){
		this.game = game;
		camera = new OrthographicCamera(1280, 720);
		shapeRenderer = new ShapeRenderer();
		font.scale(2);
		title = new Texture("Tittle.png");
		option1 = new Texture("singleplayer.png");
		option2 = new Texture("multiplayer.png");
		helpHint = new Texture("help text.png");
		twitter = new Texture("twitterShoutout.png");
		click = Gdx.audio.newSound(Gdx.files.internal("click button.mp3"));
	}
	@Override
	public void render(float delta) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setColor(1, 1, 1, 1);
		shapeRenderer.begin(ShapeType.Line);
		//shapeRenderer.box(x, y, z, width, height, depth);
		shapeRenderer.end();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			batch.draw(title,-400,100);
			batch.draw(option1, -300, -100);
			batch.draw(option2,-300,-300);
			batch.draw(helpHint,-630,-250);
			batch.draw(twitter,290,-250);
		batch.end();
		if(Gdx.input.isTouched()){
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			touchpt.x = x;
			touchpt.y = y;
			touchpt.z = 0;
			camera.unproject(touchpt);
			if(menu1.contains(touchpt.x,touchpt.y)){
				click.play(1f);
				load1P();
			}
			if(menu2.contains(touchpt.x,touchpt.y)){
				load2P();
			}
		}
	}
	public void load1P(){
		game.setScreen(new SinglePlayer(game,0,1));
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
