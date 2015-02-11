package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Missile {
	Vector2 touch;
	int viewPortWidth;
	Vector2 position;
	boolean visible;
	Vector2 startPt;

	public Missile(Vector2 touch, int vpWidth) {
		visible = true;
		this.touch = touch;
		viewPortWidth = vpWidth;
		position = new Vector2(viewPortWidth / 2, 0);
		startPt = new Vector2(viewPortWidth / 2, 0);
	}

	public void update() {
		if (touch.x > position.x) {
			position.x += 100 * Gdx.graphics.getDeltaTime();
		}
		if (touch.x < position.x) {
			position.x -= 100 * Gdx.graphics.getDeltaTime();
		}
		if (touch.y > position.y) {
			position.y += 100 * Gdx.graphics.getDeltaTime();
		}
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
