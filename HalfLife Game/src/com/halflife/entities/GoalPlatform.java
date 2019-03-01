package com.halflife.entities;

import javafx.scene.paint.Color;
import main.GameConstants;

public class GoalPlatform extends RectObject {

	public GoalPlatform(double d, double e, int width, int height) {
		super(d, e, width, height, GameConstants.TYPE_GOAL, Color.YELLOW);
	}

}
