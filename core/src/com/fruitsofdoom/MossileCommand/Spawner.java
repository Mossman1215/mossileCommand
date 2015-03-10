package com.fruitsofdoom.MossileCommand;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;

public class Spawner {
	float delay;
	float currentTime;
	LinkedList<ICBM> ICBMList;
	public Spawner(float delay,LinkedList<ICBM> list){
		this.delay = delay;
		currentTime = 0;
		ICBMList = list;
	}
	public void Update(){
		float delta = Gdx.graphics.getDeltaTime();
		currentTime+=delta;
		if(currentTime>delay){
			
		}
	}
	public void spawnWave(){
			currentTime =0;
			ICBMList.add(new ICBM(1));
	}
}
