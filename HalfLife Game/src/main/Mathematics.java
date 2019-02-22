package main;

import com.halflife.entities.RectObject;

public class Mathematics {

	
	public static int getDistance(RectObject obj1, RectObject obj2) {
		
		return (int) (obj1.getTranslateX() - obj2.getTranslateX());
	}
	
	public int getSpeed(int distance) {
		return 0;
	}
}
