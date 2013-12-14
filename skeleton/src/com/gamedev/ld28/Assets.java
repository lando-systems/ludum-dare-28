package com.gamedev.ld28;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Assets {
	public static Random random;
	public static SpriteBatch batch;
	public static ShapeRenderer shapes;

	public static Texture spritesheet;
	public static Texture titleBackground;


	public static TextureRegion[][] letters;
	public static TextureRegion[][] digits;
	public static TextureRegion[][] symbols;
	
	

	public static void load() {
		random = new Random();
		batch  = new SpriteBatch();
		shapes = new ShapeRenderer();
 
		spritesheet  = new Texture(Gdx.files.internal("data/spritesheet.png"));
		spritesheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		titleBackground = new Texture(Gdx.files.internal("data/titlescreen.png"));
		titleBackground.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		
		letters = splitAndGet(spritesheet, 8, 8, 0, 30, 26, 1);
		digits  = splitAndGet(spritesheet, 8, 8, 0, 31, 10, 1);
		symbols = splitAndGet(spritesheet, 8, 8, 10, 31, 18, 1);
	}

	public static void dispose() {

		spritesheet.dispose();
		batch.dispose();
	}

	private static TextureRegion[][] splitAndGet(Texture texture, int width, int height, int col, int row, int xTiles, int yTiles) {
		TextureRegion[][] allRegions = TextureRegion.split(texture, width, height);
		TextureRegion[][] regions = new TextureRegion[yTiles][xTiles];
		for (int y = 0; y < yTiles; ++y) {
			for (int x = 0; x < xTiles; ++x) {
				regions[y][x] = allRegions[row + y][col + x];
			}
		}
		return regions;
	}

}
