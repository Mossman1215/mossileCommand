package com.fruitsofdoom.MossileCommand;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;

public class Spawner {
	float delay;
	float currentTime;
	LinkedList<ICBM> ICBMList;
	int maxAmt;
	boolean complete;
	public Spawner(float delay,LinkedList<ICBM> list,int maxAmt){
		this.delay = delay;
		currentTime = 0;
		ICBMList = list;
		this.maxAmt = maxAmt;
		complete= false;
	}
	public void Update(){
		float delta = Gdx.graphics.getDeltaTime();
		currentTime+=delta;
		if(currentTime>delay){
			currentTime =0;
			if(ICBMList.size()<maxAmt){
				ICBMList.add(new ICBM(1));
			}
		}
		if(ICBMList.size()==maxAmt){
			complete=true;
		}
	}
}
