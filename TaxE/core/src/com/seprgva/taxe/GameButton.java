package com.seprgva.taxe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class GameButton extends GeneralButton {
	
	GameButton(Texture defaultTexture, Texture clickedTexture, int x, int y, OrthographicCamera camera){
		super(defaultTexture, clickedTexture, x, y, 16, 16, camera);
	}
}
