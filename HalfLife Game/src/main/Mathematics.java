package main;

import com.halflife.entities.RectObject;

public class Mathematics {

	
	public static int getDistance(RectObject obj1, RectObject obj2) {
		
		return Math.abs((int) (obj1.getTranslateX() - obj2.getTranslateX()));
	}
}
