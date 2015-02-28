package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Explosion {
	Vector2 position;
	float maxRadius = 85f; 
	float currentRadius = .1f;
	float speed = 20;
	boolean visible = true;
	Circle boundary ;
	public Explosion(Vector2 start){
		position = new Vector2(start.x,start.y);
		boundary = new Circle(position, currentRadius);
	}
	public void update(){
		currentRadius += speed*Gdx.graphics.getDeltaTime();
		boundary.radius = currentRadius;
		if(currentRadius>maxRadius){
			visible = false;
		}
	}
	public void render(ShapeRenderer batch){
		if(visible){
			batch.circle(position.x, position.y, currentRadius);
		}
	}
}
