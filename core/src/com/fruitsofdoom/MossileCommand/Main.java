package com.fruitsofdoom.MossileCommand;

import java.util.ArrayList;
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
	Texture building,missileBuilding;
	ShapeRenderer shapeBatch;
	Vector2 touch = new Vector2();
	OrthographicCamera camera;
	LinkedList<Missile> missileList;
	ArrayList<Missile> removeList;
	int vpHeight = 720;
	int vpWidth = 1280;

	@Override
	public void create() {
		camera = new OrthographicCamera(vpWidth, vpHeight);
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		missileList = new LinkedList<Missile>();
		building = new Texture("buildings.png");
		missileBuilding = new Texture("missilebuilding.png");
		removeList = new ArrayList<Missile>();
	}

	@Override
	public void render() {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shapeBatch.begin(ShapeType.Line);
		shapeBatch.setColor(Color.GREEN);
		if (Gdx.input.isTouched()) {
			missileList.add(new Missile(new Vector2(Gdx.input.getX(), vpHeight
					- Gdx.input.getY()), vpWidth));
		}
		for (Missile m : missileList) {
			m.update();
			m.render(shapeBatch);
		}
		for (Missile m : missileList) {
			if (!m.visible && !m.exp.visible) {
				removeList.add(m);
			}
		}
		for (Missile r : removeList) {
			missileList.remove(r);
		}
		if(removeList.size()>10){
			removeList = new ArrayList<Missile>();
		}
		shapeBatch.end();
		batch.begin();
		for (int i = 100; i < vpWidth; i += 320) {
			batch.draw(building, i, 0, 60, 48);
		}
		batch.draw(missileBuilding,vpWidth/2-75,0,100,85);
		batch.end();
	}
}
