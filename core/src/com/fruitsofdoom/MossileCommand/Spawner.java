package com.fruitsofdoom.MossileCommand;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;

public class Spawner {
	float delay;
	float currentTime;
	LinkedList<ICBM> ICBMList;
	int maxAmt;
	boolean complete;
	int runningTotal;
	public Spawner(float delay,LinkedList<ICBM> list,int maxAmt){
		this.delay = delay;
		currentTime = 0;
		ICBMList = list;
		this.maxAmt = maxAmt;
		complete= false;
		runningTotal = 0;
	}
	public void Update(){
		float delta = Gdx.graphics.getDeltaTime();
		currentTime+=delta;
		if(currentTime>delay){
			currentTime =0;
			if(runningTotal<maxAmt){
				ICBMList.add(new ICBM(1));
				runningTotal++;
			}
		}
		if(runningTotal==maxAmt){
			complete=true;
		}
	}
}
