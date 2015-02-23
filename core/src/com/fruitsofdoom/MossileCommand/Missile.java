package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Missile {
	Vector2 touch;
	int viewPortWidth;
	Vector2 position;
	boolean visible;
	Vector2 startPt;
	Vector2 speed;
	Vector2 difference;
	public Missile(Vector2 touch, int vpWidth) {
		visible = true;
		this.touch = touch;
		viewPortWidth = vpWidth;
		position = new Vector2(viewPortWidth / 2, 0);
		startPt = new Vector2(viewPortWidth / 2, 0);
		speed = new Vector2(50,50);
		difference = new Vector2();
		difference = touch.sub(position);
		difference = difference.nor();
		speed = speed.scl(difference);
	}

	public void update() {
		position.mulAdd(speed, Gdx.graphics.getDeltaTime());
		if (touch.dst(position) < 10) {
			// explode
			visible = false;
		}
	}

	public void render(ShapeRenderer batch) {
		if (visible) {
			batch.line(startPt, position);
		}
	}
}
