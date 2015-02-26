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
	Explosion exp = null;
	public Missile(Vector2 touch, int vpWidth) {
		visible = true;
		this.touch = touch;
		viewPortWidth = vpWidth;
		position = new Vector2(viewPortWidth / 2, 85);
		startPt = new Vector2(viewPortWidth / 2, 85);
		speed = new Vector2(100,100);
		difference = new Vector2(touch.x,touch.y);
		difference = difference.sub(position);
		difference = difference.nor();
		speed = speed.scl(difference);
	}

	public void update() {
		position.mulAdd(speed, Gdx.graphics.getDeltaTime());
		if (position.dst(touch) < 10 && visible){
			exp=new Explosion(position);
			visible = false;
		}
		if(exp!=null){
			exp.update();
		}
	}

	public void render(ShapeRenderer batch) {
		if (visible) {
			batch.line(startPt, position);
		}
		if(exp!=null){
			exp.render(batch);
		}
	}
}
