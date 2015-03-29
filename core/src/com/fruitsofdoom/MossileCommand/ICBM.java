package com.fruitsofdoom.MossileCommand;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ICBM {
	Vector2 target;
	int viewPortWidth;
	Vector2 position;
	boolean visible = true;
	Vector2 startPt;
	Vector2 speed;
	Vector2 difference;
	/**
	 * 
	 * @param speed sets max speed downwards
	 */
	public ICBM(float speed){
		startPt = new Vector2(MathUtils.random(-(1280/2), (1280/2)), 360);
		position = new Vector2(startPt.x,startPt.y);
		target = new Vector2(MathUtils.random(-(1280/2), (1280/2)),-360);
		this.speed = new Vector2(25*speed,25*speed);
		difference = new Vector2(target.x, target.y);
		difference = difference.sub(position);
		difference.nor();
		this.speed.scl(difference);
	}
	public void update(LinkedList<Explosion> explosions) {
		//System.out.println(this+","+position.x+","+position.y+",");
		position.mulAdd(speed, Gdx.graphics.getDeltaTime());
		if (position.dst(target) < 10 && visible){
			explosions.add(new Explosion(position));
			visible = false;
		}
	}

	public void render(ShapeRenderer batch) {
		if (visible) {
			batch.setColor(Color.YELLOW);
			batch.line(startPt,position);
		}
	}
}
