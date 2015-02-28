package com.fruitsofdoom.MossileCommand;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Building {
	Vector2 position;
	boolean damaged = false;
	boolean visible = true;
	enum direction {left, right};
	enum typeOfBuild {civilian, military};
	direction dir;
	typeOfBuild type;
	int height,width;
	Texture img;
	Rectangle boundary;
	public Building(int x, int y, typeOfBuild type,int width, int height, Texture img) {
		this.type = type;
		position = new Vector2(x, y);
		dir = dir.left;
		this.height = height;
		this.img = img;
		this.width = width;
		boundary = new Rectangle(x,y,height,width);
	}

	public void update() {
		if(position.y < 0-height){
			visible = false;
		}
		if(visible){
			if (damaged) {
				if(dir.equals(direction.right)){
					position.x += 10*Gdx.graphics.getDeltaTime();
				}else{
					position.x -= 10*Gdx.graphics.getDeltaTime();
				}
				position.y -= 10*Gdx.graphics.getDeltaTime();
			}
		}
	}

	public void render(SpriteBatch batch) {
		batch.draw(img, position.x, position.y,width,height);
	}
}
