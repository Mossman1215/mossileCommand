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
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture build, missileTurret;
	ShapeRenderer shapeBatch;
	Vector2 touch = new Vector2();
	OrthographicCamera camera;
	LinkedList<Missile> missileList;
	ArrayList<Missile> removeList;
	int vpHeight = 720;
	int vpWidth = 1280;
	Building[] buildings;
	Building missileBuilding;

	@Override
	public void create() {
		camera = new OrthographicCamera(vpWidth, vpHeight);
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		missileList = new LinkedList<Missile>();
		build = new Texture("buildings.png");
		missileTurret = new Texture("missilebuilding.png");
		removeList = new ArrayList<Missile>();
		missileBuilding = new Building(vpWidth / 2 - 75, 0,
				Building.typeOfBuild.military, 100, 85, missileTurret);
		buildings = new Building[4];
		for (int i = 0; i < 4; i++) {
			buildings[i] = new Building(i * vpWidth / 4 + 100, 0,
					Building.typeOfBuild.civilian, 60, 48, build);
		}
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
			if (!m.visible && !m.exp.visible) {
				removeList.add(m);
			}
			for (Building b : buildings) {
				if (b.boundary.contains(m.position)) {
					b.damaged = true;
				}
				if (m.exp != null) {
					if (Intersector.overlaps(m.exp.boundary,b.boundary)) {
						b.damaged = true;
					}
				}
			}
		}
		missileBuilding.update();
		for (Building b : buildings) {
			b.update();
		}
		for (Missile r : removeList) {
			missileList.remove(r);
		}
		if (removeList.size() > 10) {
			removeList = new ArrayList<Missile>();
		}
		shapeBatch.end();
		batch.begin();
		for (Building b : buildings) {
			b.render(batch);
		}
		missileBuilding.render(batch);
		batch.end();
	}
}
