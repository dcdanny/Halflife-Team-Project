package com.halflife.entities;

import java.util.ArrayList;
import main.SpriteAnimation;

/**
 * ListOfAnimations Keeps track of all animations running and deletes them if necessary.
 * @author Halflife
 */
public class ListofAnimations {
	
	private static ArrayList<SpriteAnimation> anilist=new ArrayList<SpriteAnimation>(); //The list of all running animations
	
	/**
	 * Adds the given animation to the animation list
	 * @param ani Animation to be added to the animation list
	 */
	public void addToList(SpriteAnimation ani) {
		anilist.add(ani);
		System.out.println("currently this many animations: "+anilist.size());
	}
	
	/**
	 * Removes an animation if necessary
	 * @param num Number of animations to be removed
	 */
	public void keepAllBut(int num) {
		for (int i = 0; i < anilist.size()-num; i++) {
			SpriteAnimation stoppingani=anilist.get(i);
			stoppingani.stopAnimation();
			anilist.remove(i);
		}
		System.out.println("currently this many animations: "+anilist.size());
	}
	
	/**
	 * Specifies an animation to keep
	 * @param sp Animation to keep
	 */
	public void keepOnly(SpriteAnimation sp) {
		for (int i = 0; i < anilist.size(); i++) {
			if (!anilist.get(i).equals(sp)) {
				anilist.remove(i);
			}
		}
		System.out.println("currently this many animations: "+anilist.size());
	}
	
	/**
	 * Removes all animations
	 */
	public void wipeList() {
		for (int i = 0; i < anilist.size(); i++) {
			anilist.get(i).stopAnimation();
			anilist.remove(i);
		}
		System.out.println("currently this many animations: "+anilist.size());
	}
}
