package main;

import com.halflife.entities.RectObject;

public class Mathematics {

	
	public static int getDistanceX(RectObject obj1, RectObject obj2) {
		
		return (int) (obj1.getTranslateX() - obj2.getTranslateX());
	}
	
public static int getDistanceY(RectObject obj1, RectObject obj2) {
		
		return (int) Math.abs((obj1.getTranslateY() - obj2.getTranslateY()));
	}
	
	public int getSpeed(int distance) {
		return 0;
	}
}
