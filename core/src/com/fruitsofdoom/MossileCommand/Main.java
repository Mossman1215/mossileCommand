package com.fruitsofdoom.MossileCommand;


import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeBatch;
	Vector2 touch = new Vector2();
	OrthographicCamera camera;
	ArrayList<Missile> missileList;
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		missileList = new ArrayList<Missile>();
	}

	@Override
	public void render () {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeBatch.begin(ShapeType.Line);
		shapeBatch.setColor(Color.GREEN);
		if(Gdx.input.isTouched()){
			missileList.add(new Missile(new Vector2(Gdx.input.getX(), 720-Gdx.input.getY()),1280));
		}
		for(Missile m :missileList){
			m.update();
			m.render(shapeBatch);
		}
		shapeBatch.end();
		batch.begin();
		batch.end();
	}
}
