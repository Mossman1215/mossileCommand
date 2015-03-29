package com.fruitsofdoom.MossileCommand;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

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
		position = new Vector2(25, -360+85);
		startPt = new Vector2(25, -360+85);
		speed = new Vector2(100, 100);
		difference = new Vector2(touch.x, touch.y);
		difference = difference.sub(position);
		difference = difference.nor();
		speed = speed.scl(difference);
	}

	public void update(LinkedList<Explosion> explosions) {
		position.mulAdd(speed, Gdx.graphics.getDeltaTime());
		if (position.dst(touch) < 10 && visible) {
			explosions.add(new Explosion(position));
			visible = false;
		}
	}

	public void render(ShapeRenderer batch) {
		if (visible) {
			batch.setColor(Color.GREEN);
			batch.line(startPt, position);
		}
	}
}
