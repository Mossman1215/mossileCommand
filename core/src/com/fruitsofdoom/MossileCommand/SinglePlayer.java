package com.fruitsofdoom.MossileCommand;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class SinglePlayer implements Screen {
	SpriteBatch batch;
	Texture build, missileTurret,bullet;
	ShapeRenderer shapeBatch;
	Vector2 touch = new Vector2();
	OrthographicCamera camera;
	LinkedList<Missile> missileList;
	ArrayList<Missile> removeList;
	LinkedList<ICBM> ICBMList;
	ArrayList<ICBM> removeIList;
	int vpHeight = 720;
	int vpWidth = 1280;
	Building[] buildings;
	Building missileBuilding;
	float cooldown = .5f;
	float currentTime =0;
	int wave = 0;
	float difficulty = 1.25f;
	int maxAmmo = 10;
	int currentAmmo = 10;
	float regenRate = 2f;
	float regenTime =0;
	Game game;
	Spawner missileSpawner;
	public SinglePlayer(Game game) {
		camera = new OrthographicCamera(vpWidth, vpHeight);
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		missileList = new LinkedList<Missile>();
		build = new Texture("buildings.png");
		missileTurret = new Texture("missilebuilding.png");
		bullet = new Texture("bullet.png");
		removeList = new ArrayList<Missile>();
		ICBMList = new LinkedList<ICBM>();
		removeIList = new ArrayList<ICBM>();
		missileBuilding = new Building(vpWidth / 2 - 75, 0,
				Building.typeOfBuild.military, 100, 85, missileTurret);
		buildings = new Building[4];
		for (int i = 0; i < 4; i++) {
			buildings[i] = new Building(i * vpWidth / 4 + 100, 0,
					Building.typeOfBuild.civilian, 60, 48, build);
		}
		missileSpawner = new Spawner(1.5f, ICBMList, 10);
		this.game = game;
	}

	@Override
	public void render(float delta) {
		camera.update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		currentTime+=Gdx.graphics.getDeltaTime();
		regenTime+=Gdx.graphics.getDeltaTime();
		missileSpawner.Update();
		if(missileSpawner.complete&&ICBMList.isEmpty()){
			//score
			//reset buildings
			//tell the player wtf is going on
			//you win!
			game.setScreen(new YouWin(vpWidth, vpHeight, game));
			//missileSpawner= new Spawner(missileSpawner.delay*difficulty, ICBMList, (int)(missileSpawner.maxAmt*difficulty));
		}
		shapeBatch.begin(ShapeType.Line);
		if (Gdx.input.isTouched()&&Gdx.input.justTouched()&&!missileBuilding.damaged) {
			if(currentAmmo>0){
			if(currentTime>cooldown){
				missileList.add(new Missile(new Vector2(Gdx.input.getX(), vpHeight - Gdx.input.getY()), vpWidth));
				currentTime=0;
				currentAmmo--;
				}
			}
		}
		if(regenTime>regenRate && currentAmmo<=maxAmmo){
			currentAmmo++;
			regenTime=0;
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
					m.exp = new Explosion(m.position);
					m.visible=false;
				}
				if (m.exp != null&&m.exp.visible) {
					if (Intersector.overlaps(m.exp.boundary,b.boundary)) {
						b.damaged = true;
					}
				}
			}
			for(ICBM i:ICBMList){
				if(m.exp!=null&&m.exp.visible){
					if(m.exp.boundary.contains(i.position)){
						i.visible=false;
						i.exp = new Explosion(i.position);
						i.speed=new Vector2(0, 0);
					}
				}
				if(i.exp!=null&&i.exp.visible){
					if(i.exp.boundary.contains(m.position)){
						m.visible=false;
						m.exp = new Explosion(m.position);
					}
				}
			}
		}
		for(ICBM i:ICBMList){
			if(!i.visible&&!i.exp.visible){
				removeIList.add(i);
			}
		}
		for(ICBM r : removeIList){
			ICBMList.remove(r);
		}
		if(removeIList.size()>10){
			removeIList= new ArrayList<ICBM>();
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
		for (ICBM i: ICBMList){
			i.update();
			i.render(shapeBatch);
			for(Building b:buildings){
				if(i.visible){
					if(b.boundary.contains(i.position)){
						b.damaged=true;
						i.visible=false;
						i.exp=new Explosion(i.position);
						i.speed=new Vector2(0,0);
					}
				}
				if(i.exp!=null&&i.exp.visible){
					if(Intersector.overlaps(i.exp.boundary, b.boundary)){
						b.damaged=true;
					}
					if(Intersector.overlaps(i.exp.boundary, missileBuilding.boundary)){
						missileBuilding.damaged=true;
					}
				}
			}
			if(missileBuilding.boundary.contains(i.position)){
				missileBuilding.damaged = true;
				i.exp=new Explosion(i.position);
			}
		}
		shapeBatch.end();
		batch.begin();
		for (Building b : buildings) {
			b.render(batch);
		}
		for(int i = 0; i<currentAmmo;i++){
			batch.draw(bullet, i*20, 50,10,20);
		}
		missileBuilding.render(batch);
		batch.end();
		if(missileBuilding.damaged&&!missileBuilding.visible){
			game.setScreen(new GameOver(vpWidth, vpHeight,game));
			this.dispose();
		}
	}
	
	
	@Override
	public void show() {

	}


	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeBatch.dispose();
	}

}
