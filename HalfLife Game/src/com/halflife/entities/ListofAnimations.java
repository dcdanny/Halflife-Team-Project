package com.halflife.entities;

import java.util.ArrayList;
import main.SpriteAnimation;
public class ListofAnimations {
	private static ArrayList<SpriteAnimation> anilist=new ArrayList<SpriteAnimation>();
	
	public ListofAnimations() {
		
	}
	
	
	public void addToList(SpriteAnimation ani) {
		anilist.add(ani);
		System.out.println("currently this many animations: "+anilist.size());
	}
	public void keepAllBut(int num) {
		for (int i = 0; i < anilist.size()-num; i++) {
		SpriteAnimation stoppingani=anilist.get(i);
		stoppingani.stopAnimation();
		anilist.remove(i);
		}
		System.out.println("currently this many animations: "+anilist.size());
		
	}
	public void wipeList() {
		for (int i = 0; i < anilist.size(); i++) {
			anilist.get(i).stopAnimation();
			anilist.remove(i);
		}
		System.out.println("currently this many animations: "+anilist.size());
	}
}
