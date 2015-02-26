package com.fruitsofdoom.MossileCommand;


import java.util.LinkedList;

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

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeBatch;
	Vector2 touch = new Vector2();
	OrthographicCamera camera;
	LinkedList<Missile> missileList;
	@Override
	public void create () {
		camera = new OrthographicCamera(1280, 720);
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		missileList = new LinkedList<Missile>();
		img = new Texture("buildings.png");
	}

	@Override
	public void render () {
		for(Missile m: missileList){
			if(!m.visible){
				if(!m.exp.visible){
					missileList.remove(m);
				}
			}
		}
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
		for(int i=100; i<1280; i+=320){
			batch.draw(img, i, 0);
		}
		batch.end();
	}
}
